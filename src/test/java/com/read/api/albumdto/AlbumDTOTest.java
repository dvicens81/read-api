package com.read.api.albumdto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.read.api.dto.AlbumDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlbumDTOTest {
	

	@Test
	public void testAlbumDto() {
		AlbumDTO a = new AlbumDTO();
		a.setReleaseCount(3);
		a.setTitle("Title");
		a.setId("adbcf");
		a.setYear("1988");
		
		assertEquals(a.getTitle(), "Title");
		assertEquals(a.getId(), "adbcf");
		assertEquals(a.getYear(), "1988");
	}

}
