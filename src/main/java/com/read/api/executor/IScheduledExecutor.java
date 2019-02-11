package com.read.api.executor;

import javax.net.ssl.HttpsURLConnection;

public interface IScheduledExecutor {
	
	public int getCountsByReleaseGroup(HttpsURLConnection connection, String elementToGet);
}
