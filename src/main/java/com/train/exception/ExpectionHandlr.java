package com.train.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpectionHandlr {
	
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TicketNotFoundException.class)
	public Map<String,String>  handelTicketNotFoundException(TicketNotFoundException ex)
	{
		
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("erro meassge", ex.getMessage());
		return errorMap;
		
	}

}
