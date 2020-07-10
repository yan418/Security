package com.dream.blog.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dream.blog.common.exception.ServiceException;
import com.dream.blog.common.web.GetCurrentTime;
import com.dream.blog.dao.VersionManagementDao;
import com.dream.blog.model.VersionManagement;
import com.dream.blog.service.VersionManagementService;


@Service
public class VersionManagementServiceImpl implements VersionManagementService{

	@Autowired
	private VersionManagementDao versionDao;
	
	//存放的跟目录   (获取当前路径)   private static final String BASEDIR="\\down\\";  
	private static final String BASEDIR= System.getProperty("catalina.home");
	
	@Override
	public List<VersionManagement> findList() {		
		List<VersionManagement> list = versionDao.findList();
		return list;
	}

	@Override
	public VersionManagement findOne(Integer id) {
		VersionManagement version = versionDao.findOne(id);
		return version;
	}

	@Override
	public int deleteOne(Integer id) {
		VersionManagement version = versionDao.findOne(id);
		if(version == null){
			throw new ServiceException("删除失败，没有该条记录");
		}
		if(("0").equals(version.getPlatform())) {
			String path= BASEDIR + "\\webapps"+ version.getDownloadLink(); 
			//System.out.println("path + " +path);
			Boolean lean = delAPK(path);
			if(lean==false) {
				throw new ServiceException("删除文件失败，文件名是"+ version.getDownloadLink());
			}
		}
		int row = versionDao.deleteOne(id);
		if(row!=1){
			throw new ServiceException("删除失败");
		}
		return row;
	}

	/**
	 * 安卓版本增加
	 * @param  VersionManagement
	 * @return url 要上传的文件
	 */
	@Override
	public int saveOne(VersionManagement versionManagement,MultipartFile url) {
		if(versionManagement == null){
			throw new ServiceException("数据丢失，请重试");
		}	
		
		//System.out.println("url  ++++++");
		//System.out.println(url);
		
		String path=uploadingAPK(url);
		versionManagement.setDownloadLink(path);
		//创建数据库
		Integer id = save(versionManagement);
		return id;
	}
	
	/**
	 * 安卓版本修改
	 * @param  VersionManagement
	 * @return url 要上传的文件
	 */
	@Override
	public int alterAndroid(VersionManagement versionManagement, MultipartFile url) {
		if(versionManagement == null){
			throw new ServiceException("数据丢失，请重试");
		}	
		if(url != null){
			String path=uploadingAPK(url);
			//System.out.println(path);
			versionManagement.setDownloadLink(path);
		}
		//修改
		Integer id = alter(versionManagement);		
		return id;
	}
	
	
	
	/**
	 * IOS版本增加
	 * @param versionManagement
	 * @param state 状态   set 创建  alter 修改
	 */
	@Override
	public int saveOne(VersionManagement versionManagement,String state) {
		if(versionManagement == null){
			throw new ServiceException("数据丢失，请重试");
		}
		Integer id = null;
		if(("set").equals(state)) {
			//创建
			id = save(versionManagement);
		}else if(("alter").equals(state)){
			//修改
			id = alter(versionManagement);		
		}
		return id;
	}
	
	
	/**
	 * 增加
	 * @param  VersionManagement
	 * @return 
	 */
	private int save(VersionManagement versionManagement){			
		//获取当前时间
		String time=new GetCurrentTime().getNowDate();  
		versionManagement.setCreationTime(time);	
		int row = versionDao.saveOne(versionManagement);
		if(row!=1){
			throw new ServiceException("创建失败");
		} 
		return versionManagement.getId();
	}
	
	/**
	 * 修改
	 * @param  VersionManagement
	 * @return 
	 */
	private int alter(VersionManagement versionManagement) {
		
		Integer id = versionManagement.getId();
		if(id==null || id<=0){
			throw new ServiceException("参数错误");
		}
		//获取修改时间
		String time=new GetCurrentTime().getNowDate();  
		versionManagement.setAlterTime(time);
		//System.out.println("xxx");
		//System.out.println(versionManagement);
		int row = versionDao.alterOne(versionManagement);
		if(row!=1){
			throw new ServiceException("修改失败");
		} 
		return versionManagement.getId();
	}
	
	
	/**
	 * 上传APK文件
	 * @param  mFile 写入文件
	 * @return path  存入数据库的路径
	 */
	private String uploadingAPK(MultipartFile mFile){	
		//定义路径 
		String fileDirectory=null;		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		fileDirectory =  BASEDIR + "/webapps/apk/" + sdf.format(new Date())+"/";
		//System.out.println("fileDirectory  ++++----------------------------++");
		//System.out.println(fileDirectory);
		//获得文件名后缀 
		String suffix= mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".")); 
		//构建新文件名
		String filename = UUID.randomUUID().toString() + suffix;	
		//创建目标文件对象
		File dest = new File(fileDirectory,filename);
		File parent=dest.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		try {
			mFile.transferTo(dest);	
		} catch (IOException e) {			
			throw new ServiceException("上传失败");
		}
		//获取该文件路径存进数据库中
		String replace= BASEDIR + "\\webapps"; 
		//System.out.println("sssss+++");
		//System.out.println(replace);
		//System.out.println(dest.getAbsolutePath());
		String path=dest.getAbsolutePath().replace(replace,"");		
		//System.out.println(path);
		return path;		
	}
	
	
	/**
	 * 删除文件
	 * @param  FileName //根据指定的文件名创建File对象
	 */
	private  Boolean delAPK(String FileName){	
		File file = new File(FileName);
		if ( !file.exists() ){  //要删除的文件不存在
			return false;
		}else{ //要删除的文件存在			
			if ( file.isFile() ){ 
				//如果目标文件是文件	
				return deleteFile(FileName);
			}else{  //如果目标文件是目录
				//return deleteDir(FileName);
				return false;
			}
		}

	}
	
	/**
	 * 判断指定的文件删除是否成功
	 * @param FileName 文件路径
	 * @return true or false 成功返回true，失败返回false
	 */
	public static boolean deleteFile(String fileName){
		
		//System.out.println("fileName 文件路径" +fileName);		
		File file = new File(fileName);//根据指定的文件名创建File对象		
		if ( file.exists() && file.isFile() ){ 
			//要删除的文件存在且是文件		
			if ( file.delete()){
				//System.out.println("文件"+fileName+"删除成功！");
				return true;
			}else{
				//System.out.println("文件"+fileName+"删除失败！");
				return false;
			}
		}else{
			    //System.out.println("文件"+fileName+"不存在，删除失败！" );
				return false;
		}				
	}

}


