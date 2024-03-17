package com.medicare.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseOrder {

	private Integer purchaseOrderId;
	
	private Integer purchaseOrderItemId;
	
	private Integer productId;
	
	private String productName;
    
    private Integer quantity;
    
    private BigDecimal unitPrice;
    
    private BigDecimal totalPrice;
	
    private Integer customerId;
    
    private String status;
    
    private String shippingAddress;
    
    private String billingAddress;
    
    private String shippingMethod;
	
}
