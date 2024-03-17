package com.medicare.orderservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.orderservice.dto.PurchaseItemDto;
import com.medicare.orderservice.dto.PurchaseItemsandOrderDto;
import com.medicare.orderservice.dto.PurchaseOrderDto;
import com.medicare.orderservice.dto.RequestPurchaseOrder;
import com.medicare.orderservice.dto.ResponsePurchaseOrder;
import com.medicare.orderservice.entity.PurchaseOrderItem;
import com.medicare.orderservice.response.PurchaseOrderResponse;
import com.medicare.orderservice.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/v1/purchase-order")
@CrossOrigin
public class PurchaseController {

	@Autowired
	PurchaseOrderService service;
	
	@Autowired
	PurchaseOrderResponse response;
	
	public static final Logger logger = Logger.getLogger(PurchaseController.class.getName());
	
	@PostMapping("")
	public ResponseEntity<Object> newPurchaseOrder (@RequestBody RequestPurchaseOrder reqPurchaseOrder)
	{
		if(service.newPurchaseOrder(reqPurchaseOrder))
		{
			return response.responseWithoutData("success", HttpStatus.CREATED);
		}else {
			return response.responseWithoutData("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Object> getProductItem (@PathVariable int orderId)
	{
		List<PurchaseItemDto> purcaseItemDto = service.getAllPurchaseItem(orderId);
		
		return response.responseWithListData("success", purcaseItemDto, HttpStatus.OK);
	}
	
	@GetMapping("/view-purchase-order")
	public ResponseEntity<Object> getPurchaseOrder ()
	{
		List<PurchaseOrderDto> purchaseOrderDto = service.getAllPurchaseOrder();
		
		return response.responseWithListData("success", purchaseOrderDto, HttpStatus.OK);
	}
	
	@GetMapping("/purchase-items")
	public ResponseEntity<Object> getAllPurchaseItem()
	{
		List<ResponsePurchaseOrder> purchaseItems = service.getAllOrder();
		 return response.responseWithListData("success", purchaseItems, HttpStatus.OK);
	}
	
}
