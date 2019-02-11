package com.read.api.dto;

public class AlbumDTO {
	private String title;
	private String year;
	private String id;
	private int releaseCount;
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getId() {
		return this.id;
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
