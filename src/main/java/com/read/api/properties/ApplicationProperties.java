package com.read.api.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {
	@Value("${url.musicbrainz.api}")
	private String urlConnection;
	
	@Value("${request.param.artist}")
	private String requestArtist;
	
	@Value("${request.param.type}")
	private String requestType;
	
	@Value("${request.param.limit}")
	private String requestLimit;
	
	@Value("${request.param.offset}")
	private String requestOffset;
	
	@Value("${request.param.release.groups.id}")
	private String requestReleaseGroupId;
	
	@Value("${url.musicbrainz.api.release.count}")
	private String urlReleaseByRG;
	
	public String getUrlConnection() {
		return this.urlConnection;
	}

	public String getRequestArtist() {
		return requestArtist;
	}

	public String getRequestType() {
		return requestType;
	}

	public String getRequestLimit() {
		return requestLimit;
	}

	public String getRequestOffset() {
		return requestOffset;
	}

	public String getRequestReleaseGroupId() {
		return requestReleaseGroupId;
	}

	public String getUrlReleaseByRG() {
		return urlReleaseByRG;
	}
	
}
