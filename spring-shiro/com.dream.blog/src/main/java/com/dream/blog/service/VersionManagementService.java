package com.dream.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dream.blog.model.VersionManagement;

public interface VersionManagementService {

	 public List<VersionManagement> findList();
	 
	 public VersionManagement findOne(Integer id);
	 
	 public int deleteOne(Integer id);
	 
	 public int saveOne(VersionManagement versionManagement,MultipartFile url);
	
	 public int saveOne(VersionManagement versionManagement,String state);
	 
	 public int alterAndroid(VersionManagement versionManagement,MultipartFile url);
	 
}
