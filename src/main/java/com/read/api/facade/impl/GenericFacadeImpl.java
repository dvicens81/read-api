package com.read.api.facade.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.read.api.bean.ApiResult;
import com.read.api.bean.Parameter;
import com.read.api.dto.AlbumDTO;
import com.read.api.entity.Album;
import com.read.api.exception.CustomizedException;
import com.read.api.executor.IScheduledExecutor;
import com.read.api.facade.IGenericFacade;
import com.read.api.helper.IJsonHelper;
import com.read.api.helper.IGenericHelper;
import com.read.api.helper.impl.HttpsConnectionHelper;
import com.read.api.mapper.IModelMapper;

@Component
public class GenericFacadeImpl implements IGenericFacade {
	
	private HttpsConnectionHelper connection;
	private IGenericHelper<String> mapHelper;
	private IJsonHelper<Album> jsonHelper;
	private IScheduledExecutor scheduleExecutor;
	private IModelMapper<Album, AlbumDTO> mapper;
	
	private static final String ATTRIBUTE_GROUP = "release-groups";
	private static final String ATTRIBUTE_GROUP_COUNT=  "release-group-count";
	private static final String ATTRIBUTE_RELEASE_COUNT = "release-count";
	
	private HttpsURLConnection con = null;
	
	@Autowired
	public GenericFacadeImpl(HttpsConnectionHelper connection, IGenericHelper<String> mapHelper, IJsonHelper<Album> jsonHelper,
							IScheduledExecutor scheduleExecutor, IModelMapper<Album, AlbumDTO> mapper) {
		this.connection = connection;
		this.mapHelper = mapHelper;
		this.jsonHelper = jsonHelper;
		this.scheduleExecutor = scheduleExecutor;
		this.mapper = mapper;
	}
	
	@Override
	public ApiResult getJsonArray(Parameter parameters) throws IOException {		
		con = connection.getConnection(parameters.getUrlConnection(), getMapOfItemsToSearch(parameters));
		if(!(con.getResponseCode() >= HttpStatus.OK.value() && con.getResponseCode() < HttpStatus.FOUND.value())) throw new CustomizedException();
		JsonObject jsonObject = jsonHelper.getJsonObject(con);
		return new ApiResult(jsonHelper.getJsonArray(jsonObject, ATTRIBUTE_GROUP), 
							jsonHelper.getJsonElement(jsonObject, ATTRIBUTE_GROUP_COUNT).getAsInt());
	}
	
	@Override
	public Page<AlbumDTO> getAlbumsDTO(JsonArray jsonArray,Parameter parameters) {
		List<Album> lAlbums = jsonHelper.getListOfItems(jsonArray, Album.class);
		List<AlbumDTO> lAlbumsDto = new LinkedList<>();
		lAlbums.forEach(x->{
			parameters.setValues(x.getId());
			this.con = connection.getConnection(parameters.getUrlConnection() ,getMapOfItemsToSearch(parameters));
			try {
				if (con.getResponseCode() >= HttpStatus.OK.value() && con.getResponseCode() < HttpStatus.FOUND.value()) {
					x.setReleaseCount(scheduleExecutor.getCountsByReleaseGroup(con, ATTRIBUTE_RELEASE_COUNT));
					lAlbumsDto.add(mapper.convertEntityToDto(x, AlbumDTO.class));
				}
			} catch (NullPointerException | IOException e) {
				throw new CustomizedException();
			} finally {
				con.disconnect();
			}
		});
		return new PageImpl<>(lAlbumsDto, new PageRequest(parameters.getPage(), lAlbumsDto.size()), parameters.getTotal());
	}
	
	private Map<String, String> getMapOfItemsToSearch(Parameter parameters){
		return mapHelper.createMap(mapHelper.createList(parameters.getKeys()), mapHelper.createList(parameters.getValues()));
	}
}
