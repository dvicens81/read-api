package com.read.api.service;

import org.springframework.data.domain.Page;

import com.read.api.dto.AlbumDTO;

public interface IAlbumService {

	public Page<AlbumDTO> findReleaseGroupByArtist(String idArtist, String type, int limit, int offset);
}
