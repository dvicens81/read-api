package com.read.api.bean;

import com.google.gson.JsonArray;

public class ApiResult {
	
	private JsonArray jsonArray;
	private int total;
	
	public ApiResult(JsonArray jsonArray, int total) {
		this.jsonArray = jsonArray;
		this.total = total;
	}
	public JsonArray getJsonArray() {
		return jsonArray;
	}
	public int getTotal() {
		return total;
	}
}
