package com.read.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.read.api.dto.AlbumDTO;
import com.read.api.facade.IAlbumFacade;

@Service
public class AlbumServiceImpl implements IAlbumService{
	
	@Autowired
	private IAlbumFacade facade;

	@Override
	public Page<AlbumDTO> findReleaseGroupByArtist(String idArtist, String type, int limit, int offset){
		return facade.getAlbumsByArtist(idArtist, type, limit, offset);
	}	

}
