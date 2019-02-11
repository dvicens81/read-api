package com.read.api.facade;

import java.io.IOException;

import org.springframework.data.domain.Page;

import com.google.gson.JsonArray;
import com.read.api.bean.ApiResult;
import com.read.api.bean.Parameter;
import com.read.api.dto.AlbumDTO;

public interface IGenericFacade {
	
	public ApiResult getJsonArray(Parameter parameters) throws IOException;
	
	public Page<AlbumDTO> getAlbumsDTO(JsonArray jsonArray,Parameter parameters);

}
