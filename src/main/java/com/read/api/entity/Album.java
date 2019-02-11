package com.read.api.entity;

import com.google.gson.annotations.SerializedName;

public class Album {
	
	@SerializedName(value="title")
	private String title;
	@SerializedName(value="first-release-date")
	private String year;
	@SerializedName(value="id")
	private String id;
	
	private int releaseCount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReleaseCount() {
		return releaseCount;
	}

	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}	
}
