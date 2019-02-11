package com.read.api.unit.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.read.api.helper.ConnectionHelper;
import com.read.api.helper.impl.HttpsConnectionHelper;

public class ConnectionApiTest {
	
	private ConnectionHelper helper;
	private  Map<String, String> mapVariablesToReplace;
	
	@BeforeEach
	public void setUp() {
		helper = new HttpsConnectionHelper();
		mapVariablesToReplace = new HashMap<String, String>();
		mapVariablesToReplace.put("<id_artist>", "65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab");
		mapVariablesToReplace.put("<type>","album");
		mapVariablesToReplace.put("<limit>","50");
		mapVariablesToReplace.put("<offset>","3");

	}
	
	@Test
	public void testConnection() throws IOException {
		String url = "https://musicbrainz.org/ws/2/release-group?artist=<id_artist>&type=&limit=<limit>&offset=<offset>&fmt=json";
		HttpsURLConnection connection = helper.getConnection(url, mapVariablesToReplace);
		assertEquals(connection.getResponseCode(), HttpStatus.OK.value());
	}

}
