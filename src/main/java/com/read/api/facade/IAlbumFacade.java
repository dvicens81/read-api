package com.read.api.facade;

import org.springframework.data.domain.Page;

import com.read.api.dto.AlbumDTO;

public interface IAlbumFacade {
	
	public Page<AlbumDTO> getAlbumsByArtist(String idArtist, String type, int limit, int offset);

}
