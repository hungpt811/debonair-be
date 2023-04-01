package com.training.debonair.dto;

public class CategorySearchModel extends BaseSearchModel {
	// tìm theo keyword
	public String keyword;
	public String seo;

	public Boolean isHot;

	public String getKeyword() {
		return keyword;
	}

	public String getSeo() {
		return seo;
	}

	
	
}
