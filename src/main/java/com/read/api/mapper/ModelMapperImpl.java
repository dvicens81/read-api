package com.read.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl<E,T> implements IModelMapper<E, T> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public T convertEntityToDto(E item, Class<T> nameClass) {
		return modelMapper.map(item, nameClass);
	}
	
}
