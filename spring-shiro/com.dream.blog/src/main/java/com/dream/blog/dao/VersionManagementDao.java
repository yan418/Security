package com.dream.blog.dao;

import java.util.List;

import com.dream.blog.model.VersionManagement;

public interface VersionManagementDao {
	
	
	 public List<VersionManagement> findList();
	 
	 public VersionManagement findOne(Integer id);
	 
	 public int alterOne(VersionManagement versionManagement);
	 
	 public int deleteOne(Integer id);
	 
	 public int saveOne(VersionManagement versionManagement);
	 
}
