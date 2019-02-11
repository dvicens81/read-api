package com.read.api.bean;

public class Parameter {
	
	private String[] keys;
	private String[] values;
	private String urlConnection;
	private int total;
	private int page;
	
	public Parameter(String[] keys, String[] values, String urlConnection) {
		this.keys = keys;
		this.values = values;
		this.urlConnection = urlConnection;
	}

	public Parameter(String[] keys, String[] values, String urlConnection, int total, int page) {
		this.keys = keys;
		this.values = values;
		this.urlConnection = urlConnection;
		this.total = total;
		this.page = page;
	}

	public String[] getKeys() {
		return keys;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String... values) {
		this.values = values;
	}

	public String getUrlConnection() {
		return urlConnection;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}
}
