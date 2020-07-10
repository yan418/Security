package com.dream.blog.model;

import java.io.Serializable;

/**
 * 版本控制实体
 * @author yan
 *
 */
public class VersionManagement implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -911808094831321910L;
	
	private Integer id;
	//平台
	private String platform;
	//版本号
	private String version;
	//版本说明
	private String imprint;
	//下载地址
	private String downloadLink;
	//商店地址
	private String storeAddress;
	//创建时间
	private String creationTime;
	//修改时间
	private String alterTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getImprint() {
		return imprint;
	}
	public void setImprint(String imprint) {
		this.imprint = imprint;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}
	@Override
	public String toString() {
		return "VersionManagement [id=" + id + ", platform=" + platform + ", version=" + version + ", imprint="
				+ imprint + ", downloadLink=" + downloadLink + ", storeAddress=" + storeAddress + ", creationTime="
				+ creationTime + ", alterTime=" + alterTime + "]";
	}
	
	
}
