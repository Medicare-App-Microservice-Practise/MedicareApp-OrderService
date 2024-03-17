package com.medicare.orderservice.dto;

import java.math.BigDecimal;

import com.medicare.orderservice.entity.PurchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemsandOrderDto {

	private Integer purchaseOrderItemId;
	
	private Integer productId;
    
    private String productName;
    
    private Integer quantity;
    
    private BigDecimal unitPrice;
    
    private BigDecimal totalPrice;
    
    private PurchaseOrder purchaseOrder;
	
}
