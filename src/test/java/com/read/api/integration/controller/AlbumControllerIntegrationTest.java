package com.read.api.integration.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.read.api.dto.AlbumDTO;
import com.read.api.service.IAlbumService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlbumControllerIntegrationTest {
	
	@Autowired
	private WebApplicationContext wap;
	private MockMvc mockMvc;
	
	@MockBean
	private IAlbumService albumService;
	
	private List<AlbumDTO> lAlbumsDto;
	
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
		lAlbumsDto = new ArrayList<AlbumDTO>();
	}
	
	@Test
	public void findAlbumDto() throws Exception {
		when(albumService.findReleaseGroupByArtist("65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", "album", 50, 1)).thenReturn(new PageImpl<AlbumDTO>(lAlbumsDto));		
		
		mockMvc.perform(get("/api/read/artist/65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab?type=album&limit=50&offset=1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		//NOT ACCEPTED
		ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
		Collection<Future<?>> futures = new LinkedList<>();	
		
		Runnable task = () -> {			
				try {
					mockMvc.perform(get("/api/read/artist/65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab?type=album&limit=49&offset=1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotAcceptable());
				} catch (Exception e) {
					
				}
		};
		
		futures.add(es.schedule(task, 1, TimeUnit.SECONDS));	   
		futures.iterator().next().get();
	}
	

}
