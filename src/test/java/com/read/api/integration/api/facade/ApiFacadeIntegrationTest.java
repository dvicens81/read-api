package com.read.api.integration.api.facade;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.read.api.bean.ApiResult;
import com.read.api.bean.Parameter;
import com.read.api.dto.AlbumDTO;
import com.read.api.exception.CustomizedException;
import com.read.api.facade.IGenericFacade;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiFacadeIntegrationTest {
	
	@Autowired
	private IGenericFacade facade;
	
	@Test()
	public void getJsonArray() throws IOException {
		Parameter p = new Parameter(new String[]{"<id_artist>", "<type>","<limit>", "<offset>"}, 
										new String[]{"65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", 
														"album","2", "5"}, 
										"https://musicbrainz.org/ws/2/release-group?artist=<id_artist>&type=<type>&limit=<limit>&offset=<offset>&fmt=json");
		ApiResult result = facade.getJsonArray(p);
		assertNotNull(result);
		assertEquals(result.getJsonArray().size(), 2);
	}
	
	@Test
	public void getJsonArrayConnectionNull() throws IOException {
		Parameter p = new Parameter(new String[]{"<id_artist>", "<type>","<limit>", "<offset>"}, 
										new String[]{"65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", 
														"album","2", "5"}, 
										"");
		
		assertThrows(NullPointerException.class, ()->{
			facade.getJsonArray(p);
            });
	}
	
	@Test
	public void getJsonArrayConnectionHttpCode400() throws IOException {
		Parameter p = new Parameter(new String[]{"<id_artist>", "<type>","<limit>", "<offset>"}, 
										new String[]{"65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", 
														"album","2", "5"}, 
										"https://musicbrainz.org/ws/2/release-group?artist=123");
		
		assertThrows(CustomizedException.class, ()->{
			facade.getJsonArray(p);
            });
	}
	
	@Test
	public void getPageAlbumDto() {
		//Declare JSON ARRAY
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("release-group-count", 3);

		JsonArray jsonArray = new JsonArray();
		JsonObject item = new JsonObject();
		item.addProperty("title", "Mandatory Metallica");
		item.addProperty("id", "02546843-b2b1-4c7f-a31a-23c31e8133bc");
		item.addProperty("first-release-date", "1988");
		jsonArray.add(item);

		jsonObject.add("release-groups", jsonArray);
		//Declare parameter
		Parameter p = new Parameter(new String[]{"<release_groups>"}, 
										null, 
											"https://musicbrainz.org/ws/2/release?release-group=<release_groups>&fmt=json",
												50,5);
		//Execute
		Page<AlbumDTO> albumDtos = facade.getAlbumsDTO(jsonArray,  p);
		assertNotNull(albumDtos);
		assertEquals(albumDtos.getContent().get(0).getReleaseCount(), 2);
	}
	
	@Test
	public void getPageAlbumDtoNotConnection() {
		//Declare JSON ARRAY
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("release-group-count", 3);

		JsonArray jsonArray = new JsonArray();
		JsonObject item = new JsonObject();
		item.addProperty("title", "Mandatory Metallica");
		item.addProperty("id", "02546843-b2b1-4c7f-a31a-23c31e8133bc");
		item.addProperty("first-release-date", "1988");
		jsonArray.add(item);

		jsonObject.add("release-groups", jsonArray);
		//Declare parameter
		Parameter p = new Parameter(new String[]{"<release_groups>"}, 
										null, 
											"",
												50,5);
		//Execute
		assertThrows(NullPointerException.class, ()->{
			facade.getAlbumsDTO(jsonArray,  p);
            });
	}

}
