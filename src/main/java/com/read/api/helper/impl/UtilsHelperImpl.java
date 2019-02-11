package com.read.api.helper.impl;

import org.springframework.stereotype.Component;

import com.read.api.helper.IUtilsHelper;

@Component
public class UtilsHelperImpl implements IUtilsHelper{

	@Override
	public boolean isLimitAndOffset(int limit, int offset) {
		return (limit >= 50 && offset > 0);
	}

}
