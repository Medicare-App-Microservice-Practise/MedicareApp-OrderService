package com.medicare.orderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.orderservice.dto.PurchaseItemDto;
import com.medicare.orderservice.dto.PurchaseItemsandOrderDto;
import com.medicare.orderservice.dto.PurchaseOrderDto;
import com.medicare.orderservice.dto.RequestPurchaseOrder;
import com.medicare.orderservice.dto.ResponsePurchaseOrder;
import com.medicare.orderservice.entity.PurchaseOrder;
import com.medicare.orderservice.entity.PurchaseOrderItem;
import com.medicare.orderservice.mapper.PurchaseOrderMapper;
import com.medicare.orderservice.repository.PurchaseOrderItemRepository;
import com.medicare.orderservice.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository repo;
	
	@Autowired
	PurchaseOrderItemRepository repoorderitem;
	
	@Autowired
	PurchaseOrderMapper mapper;
	
	private static final Logger logger = Logger.getAnonymousLogger();
	
	public Boolean newPurchaseOrder(RequestPurchaseOrder requestPurchaseOrder)
	{
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		
		purchaseOrder.setCustomerId(requestPurchaseOrder.getCustomerId());
		purchaseOrder.setStatus("Pending");
		purchaseOrder.setBillingAddress(requestPurchaseOrder.getBillingAddress());
		purchaseOrder.setShippingAddress(requestPurchaseOrder.getShippingAddress());
		purchaseOrder.setShippingMethod(requestPurchaseOrder.getShippingMethod());
		
		purchaseOrder = repo.save(purchaseOrder);
		
		List<PurchaseItemDto> purchaseItemDto = requestPurchaseOrder.getPurchaseItemDto();
		
		List<PurchaseItemDto> updatedpurchaseItemDto = new ArrayList<>();
		for(PurchaseItemDto purchase: purchaseItemDto)
		{
			BigDecimal totalPrice = calculateTotalPrice(purchase.getUnitPrice(),purchase.getQuantity());		
			purchase.setTotalPrice(totalPrice);
			updatedpurchaseItemDto.add(purchase);
		}
		
		List<PurchaseOrderItem> purchaseOrderItem = mapper.ListPuchaseDtoToPurchaseOrderItem(updatedpurchaseItemDto);
		
		for(PurchaseOrderItem purchase: purchaseOrderItem)
		{
			purchase.setPurchaseOrder(purchaseOrder);
		}
		
		if(repoorderitem.saveAll(purchaseOrderItem)!= null)
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<PurchaseItemDto> getAllPurchaseItem(int id)
	{
		List<PurchaseOrderItem> purchaseOrderItem = repoorderitem.getPurchaseOrderItem(id);
		
		List<PurchaseItemDto> purchaseItemDto = mapper.ListPuchaseOrderItemToPurchaseODto(purchaseOrderItem);
		
		return purchaseItemDto;
	}
	
	public List<PurchaseOrderDto> getAllPurchaseOrder()
	{
		List<PurchaseOrder> purchaseOrder = repo.findAll();
		List<PurchaseOrderDto> purchaseOrderDto = mapper.ListpurchaseOrderToPurchaseOrderDto(purchaseOrder);
		return purchaseOrderDto;
		
	}
	
	public List<ResponsePurchaseOrder> getAllOrder()
	{

	    List<PurchaseOrderItem> purchaseOrderItem = repoorderitem.findAll();
	    
	    List<ResponsePurchaseOrder> finalPurchase = new ArrayList<>();
	    for(PurchaseOrderItem purchase: purchaseOrderItem)
	    {
	    	ResponsePurchaseOrder order = new ResponsePurchaseOrder();
	    	
	    	order.setPurchaseOrderItemId(purchase.getPurchaseOrderItemId());
	    	order.setProductId(purchase.getProductId());
	    	order.setProductName(purchase.getProductName());
	    	order.setQuantity(purchase.getQuantity());
	    	order.setUnitPrice(purchase.getUnitPrice());
	    	order.setTotalPrice(purchase.getTotalPrice());
	    	
	    	PurchaseOrder purchaseOrder = purchase.getPurchaseOrder();
	    	if(purchaseOrder != null)
	    	{
	    		order.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
	    		order.setCustomerId(purchaseOrder.getCustomerId());
	    		order.setStatus(purchaseOrder.getStatus());
	    		order.setBillingAddress(purchaseOrder.getBillingAddress());
	    		order.setShippingAddress(purchaseOrder.getBillingAddress());
	    		order.setShippingMethod(purchaseOrder.getShippingMethod());
	    	}
	    	
	    	finalPurchase.add(order);
	    }
	    return finalPurchase;
	}
	
	public BigDecimal calculateTotalPrice(BigDecimal unitPrice, Integer quantity) {
		
		BigDecimal totalPrice = unitPrice.multiply(new BigDecimal(quantity));
		return totalPrice;
	}
	
}
