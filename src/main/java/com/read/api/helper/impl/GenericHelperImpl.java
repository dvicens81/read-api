package com.read.api.helper.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.read.api.helper.IGenericHelper;
@Component
public class GenericHelperImpl<T> implements IGenericHelper<T> {

	@Override
	public List<T> createList(@SuppressWarnings("unchecked") T... items) {
		return Arrays.asList(items);
	}

	@Override
	public Map<T,T> createMap(List<T> keys, List<T> values) {
		return IntStream.range(0, Math.min(keys.size(), values.size()))
			    .boxed()
			    .collect(Collectors.toMap(keys::get, values::get));
	}

}
