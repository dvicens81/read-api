package com.read.api.helper.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.read.api.helper.ConnectionHelper;

@Component
public class HttpsConnectionHelper extends ConnectionHelper {

	@Override
	public HttpsURLConnection getConnection(String url, Map<String, String> mapVariablesToReplace) {
		HttpsURLConnection connection = null; 
		try {
			connection = (HttpsURLConnection)new URL(getUrl(url, mapVariablesToReplace)).openConnection();
		} catch (IOException e) {
			connection = null;
		}
		return connection;		
	}
	
	private String getUrl(String url, Map<String, String> mapVariablesToReplace) {
		Function<String, String> combined = mapVariablesToReplace.entrySet().stream()
			    .reduce(
			        Function.identity(),
			        (f, e) -> s -> f.apply(s).replaceAll(e.getKey(), e.getValue()),
			        Function::andThen
			    );
		return combined.apply(url);
	}

}
