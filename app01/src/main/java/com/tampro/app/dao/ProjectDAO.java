package com.tampro.app.dao;

import java.util.List;
import java.util.Map;

import com.tampro.app.entity.Project;
import com.tampro.app.utils.Paging;

public interface ProjectDAO  {
	
	List<Project> findAllProjectByAccount(Map<String, Object> mapParams, long accountId , StringBuilder builder , Paging paging) ;

}
