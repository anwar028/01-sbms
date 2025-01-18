package com.train.responce;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.train.binding.TicketBinding;

public class ResponceHandler {
	
	public static ResponseEntity<TicketBinding> responceBuilder(
			String message,HttpStatus httpStatus, Object respone)
	{
		Map<String,Object> responce=new HashMap();
		responce.put("message : ", message);
		responce.put("httpStatus : ", httpStatus);
		responce.put("data : ", respone);
		
		return new ResponseEntity<TicketBinding>(httpStatus);	
		
	}
	

}
