package com.read.api.facade.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.read.api.bean.ApiResult;
import com.read.api.bean.Parameter;
import com.read.api.dto.AlbumDTO;
import com.read.api.exception.CustomizedException;
import com.read.api.facade.IAlbumFacade;
import com.read.api.facade.IGenericFacade;
import com.read.api.properties.ApplicationProperties;

@Component
public class AlbumFacadeImpl implements IAlbumFacade {
	
	@Autowired
	private IGenericFacade facade;
	@Autowired
	private ApplicationProperties properties;

	@Override
	public Page<AlbumDTO> getAlbumsByArtist(String idArtist, String type, int limit, int offset) {
		ApiResult result = null;
		try {
			result = facade.getJsonArray(new Parameter(new String[]{properties.getRequestArtist(), properties.getRequestType(),properties.getRequestLimit(), properties.getRequestOffset()}, 
																				new String[]{idArtist, type,String.valueOf(limit), String.valueOf(offset)}, 
																					properties.getUrlConnection()));
		
			return facade.getAlbumsDTO(result.getJsonArray(), new Parameter(new String[]{properties.getRequestReleaseGroupId()}, 
																							null, 
																								properties.getUrlReleaseByRG(),
																									result.getTotal(),offset-1));
		} catch (NullPointerException | IOException e) {
			throw new CustomizedException();
		}
	}

}
