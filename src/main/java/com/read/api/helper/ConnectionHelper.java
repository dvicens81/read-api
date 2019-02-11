package com.read.api.helper;

import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;

import com.read.api.properties.ApplicationProperties;

public abstract class ConnectionHelper {
	@Autowired
	protected ApplicationProperties properties;
		
	public abstract HttpsURLConnection getConnection(String url, Map<String, String> mapVariablesToReplace);
}
