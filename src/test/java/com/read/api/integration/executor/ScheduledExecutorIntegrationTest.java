package com.read.api.integration.executor;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.read.api.exception.CustomizedException;
import com.read.api.executor.IScheduledExecutor;
import com.read.api.helper.impl.HttpsConnectionHelper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ScheduledExecutorIntegrationTest {
	
	@Autowired
	private IScheduledExecutor executor;
	@Autowired
	private HttpsConnectionHelper connection;
	
	@Test
	public void testNumber() {
		String url = "https://musicbrainz.org/ws/2/release?release-group=3d00fb45-f8ab-3436-a8e1-b4bfc4d66913&fmt=json";
		String property = "release-count";
		HttpsURLConnection a = connection.getConnection(url, new HashMap<>());
		int number = executor.getCountsByReleaseGroup(a, property);
		assertNotEquals(number, 0);		
	}
	
	@Test
	public void testNumberNotWorking() {
		String url = "https://musicbrainz.org/ws/2/release?release-group=3d00fb45-f8ab-3436-a8e1-b4bfc4d66913&fmt=json";
		String property = "release-count2";
		HttpsURLConnection a = connection.getConnection(url, new HashMap<>());
		assertThrows(CustomizedException.class, ()->{
			executor.getCountsByReleaseGroup(a, property);
        });
	}
	

}
