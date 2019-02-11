package com.read.api.helper;

import java.util.List;
import java.util.Map;

public interface IGenericHelper<T> {
	
	public Map<T,T> createMap(List<T> keys, List<T>values);
	
	public List<T> createList(@SuppressWarnings("unchecked") T... items);
	
}
