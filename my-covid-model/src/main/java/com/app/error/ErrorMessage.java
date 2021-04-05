package com.app.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorMessage extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErrorMessage(Exception e) {
		super(e);
		log.error(" ErrorMessage :" + e);
	}

}
