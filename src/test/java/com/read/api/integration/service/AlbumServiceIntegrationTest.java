package com.read.api.integration.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.read.api.dto.AlbumDTO;
import com.read.api.service.IAlbumService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlbumServiceIntegrationTest {
	
	@Autowired
	private IAlbumService albumService;
	
	@Test
	public void getPageAlbumsDtos() {
		Page<AlbumDTO> albumDtos = albumService.findReleaseGroupByArtist("65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab", "album", 3, 3);
		assertNotNull(albumDtos);
		assertEquals(albumDtos.getContent().size(), 3);		
	}
}
