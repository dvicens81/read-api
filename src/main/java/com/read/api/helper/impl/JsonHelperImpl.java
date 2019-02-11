package com.read.api.helper.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.read.api.helper.IJsonHelper;

@Component
public class JsonHelperImpl<T> implements IJsonHelper<T> {

	@Override
	public List<T> getListOfItems(JsonArray jsonArray, Class<T> className) {
		Type typeOfT = TypeToken.getParameterized(List.class, className).getType();
		return new Gson().fromJson(jsonArray,typeOfT);

	}
	
	@Override
	public JsonArray getJsonArray(JsonObject object, String groupToRead) throws IOException {
		return object.get(groupToRead).getAsJsonArray();
	}

	@Override
	public JsonElement getJsonElement(JsonObject object, String property) throws IOException {
		return object.get(property);
	}

	@Override
	public JsonObject getJsonObject(HttpsURLConnection con) throws IOException {
		return new JsonParser().parse(new JsonReader(getBufferedReader(con))).getAsJsonObject();
	}

	@Override
	public JsonElement getJsonElement(HttpsURLConnection con, String property) throws IOException {
		return new JsonParser().parse(new JsonReader(getBufferedReader(con))).getAsJsonObject().get(property);
	}
	
	private BufferedReader getBufferedReader(HttpsURLConnection con) throws IOException {
		return new BufferedReader(new InputStreamReader(con.getInputStream()));
	}
}
