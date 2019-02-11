package com.read.api.mapper;

public interface IModelMapper<E, T> {

	T convertEntityToDto(E item, Class<T> nameClass);
}
