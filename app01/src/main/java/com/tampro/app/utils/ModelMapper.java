package com.tampro.app.utils;

import java.util.List;

import org.springframework.data.domain.Page;

public class ModelMapper<T> {
	private List<T> listInstance;
	private T instance ;
	private Paging paging;
	private Page<T> pageInstance;

	public ModelMapper() {
		super();
	}


	public ModelMapper(List<T> listInstance, Paging paging) {
		super();
		this.listInstance = listInstance;
		this.paging = paging;
	}


	public List<T> getListInstance() {
		return listInstance;
	}

	public void setListInstance(List<T> listInstance) {
		this.listInstance = listInstance;
	}

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}
	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}


	public Page<T> getPageInstance() {
		return pageInstance;
	}


	public void setPageInstance(Page<T> pageInstance) {
		this.pageInstance = pageInstance;
	}




	 

	 
	
	
	
	 
	 
	
	
	
	
}
