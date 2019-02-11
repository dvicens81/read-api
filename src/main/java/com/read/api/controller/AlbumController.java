package com.read.api.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.read.api.dto.AlbumDTO;
import com.read.api.exception.ArgumentsNoValidException;
import com.read.api.helper.IUtilsHelper;
import com.read.api.service.IAlbumService;
import com.weddini.throttling.Throttling;

@RequestMapping("/api/read")
@RestController
public class AlbumController {
	
	private static final int RATE_LIMIT = 1;
	
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IUtilsHelper utilsHelper;
	
	@GetMapping(value="/artist/{idArtist}")
	@Throttling(limit = RATE_LIMIT, timeUnit = TimeUnit.SECONDS)
	public ResponseEntity<Page<AlbumDTO>> findAlbumByArtist(@PathVariable String idArtist, @RequestParam("type") String type, 
												@RequestParam(value="limit", defaultValue="50") int limit, 
													@RequestParam(value="offset", defaultValue="1") int offset){
		if(!utilsHelper.isLimitAndOffset(limit, offset)) throw new ArgumentsNoValidException();
		return new ResponseEntity<>(albumService.findReleaseGroupByArtist(idArtist,type,limit,offset), HttpStatus.OK);
	}

}
