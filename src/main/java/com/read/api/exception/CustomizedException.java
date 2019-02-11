package com.read.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CustomizedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//TODO SEND CUSTOMIZE MESSAGE

}
