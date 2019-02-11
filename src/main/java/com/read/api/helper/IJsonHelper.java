package com.read.api.helper;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IJsonHelper<T> {

	JsonArray getJsonArray(JsonObject object, String groupToRead) throws IOException;
	
	JsonElement getJsonElement(JsonObject object, String property) throws IOException;
	
	List<T> getListOfItems(JsonArray jsonArray, Class<T> className);
	
	JsonObject getJsonObject(HttpsURLConnection con) throws IOException;
	
	JsonElement getJsonElement(HttpsURLConnection con, String property) throws IOException;
	
}
