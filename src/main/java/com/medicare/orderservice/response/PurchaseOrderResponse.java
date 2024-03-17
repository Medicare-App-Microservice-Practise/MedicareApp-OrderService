package com.medicare.orderservice.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderResponse {

	public ResponseEntity<Object> responseWithoutData (String message, HttpStatus httpStatus)
	{
		Map<String, Object> response = new HashMap<>();
		
		response.put("result", message);
		
		return new ResponseEntity<Object>(response,httpStatus);
	}
	
	public ResponseEntity<Object> responseWithListData (String result, List<?> dataList, HttpStatus httpStatus)
	{
		Map<String,Object> response = new HashMap<>();
		
		response.put("result", result);
		response.put("data", dataList);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
}

