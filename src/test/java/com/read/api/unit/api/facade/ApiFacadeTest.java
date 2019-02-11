package com.read.api.unit.api.facade;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.read.api.bean.ApiResult;
import com.read.api.bean.Parameter;
import com.read.api.dto.AlbumDTO;
import com.read.api.entity.Album;
import com.read.api.executor.IScheduledExecutor;
import com.read.api.facade.IGenericFacade;
import com.read.api.facade.impl.GenericFacadeImpl;
import com.read.api.helper.IJsonHelper;
import com.read.api.helper.IGenericHelper;
import com.read.api.helper.impl.HttpsConnectionHelper;
import com.read.api.mapper.IModelMapper;

public class ApiFacadeTest {
	
	private IGenericFacade facade;
	private HttpsConnectionHelper connection;
	private IJsonHelper<Album> jsonHelper;
	
	private IGenericHelper<String> mapHelper;
	private IScheduledExecutor scheduleExecutor;
	private IModelMapper<Album, AlbumDTO> mapper;
	
	private JsonObject jsonObject;
	private JsonArray jsonArray;
	
	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() {
		connection = Mockito.mock(HttpsConnectionHelper.class);
		jsonHelper = Mockito.mock(IJsonHelper.class);
		mapHelper = Mockito.mock(IGenericHelper.class);
		scheduleExecutor = Mockito.mock(IScheduledExecutor.class);
		mapper = Mockito.mock(IModelMapper.class);
		facade = new GenericFacadeImpl(connection,mapHelper,jsonHelper,scheduleExecutor,mapper);
		
		//CREATE JSON OBJECT
		jsonObject = new JsonObject();
		jsonObject.addProperty("release-group-count", 3);

		jsonArray = new JsonArray();
		JsonObject item = new JsonObject();
		item.addProperty("title", "Mandatory Metallica");
		item.addProperty("id", "02546843-b2b1-4c7f-a31a-23c31e8133bc");
		item.addProperty("first-release-date", "1988");
		jsonArray.add(item);

		jsonObject.add("release-groups", jsonArray);
	}
	
	@Test
	public void getJsonArray() throws IOException {
		String url = "https://musicbrainz.org/ws/2/release-group?artist=65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab&type=album&fmt=json";
		HttpsURLConnection urlConnection = (HttpsURLConnection)new URL(url).openConnection();
		Mockito.when(connection.getConnection(url, new HashMap<String, String>())).thenReturn(urlConnection);
		Mockito.when(jsonHelper.getJsonObject(urlConnection)).thenReturn(jsonObject);
		Mockito.when(jsonHelper.getJsonArray(jsonObject, "release-groups")).thenReturn(jsonArray);
		Mockito.when(jsonHelper.getJsonElement(jsonObject, "release-group-count")).thenReturn(jsonObject.get("release-group-count"));
		
		ApiResult result = facade.getJsonArray(new Parameter(new String[3],new String[3],url));
		assertNotNull(result);
		assertEquals(result.getTotal(), 3);
		assertEquals(result.getJsonArray(), jsonArray);
		
	}
	
	@Test
	public void getPageAlbumDto() throws IOException {
		//Declare album
		Album a = new Album();
		a.setId("02546843-b2b1-4c7f-a31a-23c31e8133bc");
		a.setTitle("Mandatory Metallica");
		a.setYear("1988");
		List<Album> lAlbums = new ArrayList<Album>();
		lAlbums.add(a);
		//Declare AlbumDTO
		AlbumDTO dto = new AlbumDTO();
		dto.setReleaseCount(3);
		dto.setId("02546843-b2b1-4c7f-a31a-23c31e8133bc");
		dto.setTitle("Mandatory Metallica");
		dto.setYear("1988");
		//Declare HttpsConnection
		String url = "https://musicbrainz.org/ws/2/release?release-group=02546843-b2b1-4c7f-a31a-23c31e8133bc&fmt=json";
		HttpsURLConnection urlConnection = (HttpsURLConnection)new URL(url).openConnection();
		//Mockitos
		Mockito.when(jsonHelper.getListOfItems(jsonArray, Album.class)).thenReturn(lAlbums);
		Mockito.when(connection.getConnection(url, new HashMap<String, String>())).thenReturn(urlConnection);
		Mockito.when(scheduleExecutor.getCountsByReleaseGroup(urlConnection, "release-count")).thenReturn(3);
		Mockito.when(mapper.convertEntityToDto(a, AlbumDTO.class)).thenReturn(dto);
		//Execute the code
		Page<AlbumDTO> result = facade.getAlbumsDTO(jsonArray, new Parameter(new String[3],new String[3],url));
		assertNotNull(result);
		assertEquals(result.getContent().size(), 1);
		assertEquals(result.getContent().get(0).getReleaseCount(), 3);
	}

}
