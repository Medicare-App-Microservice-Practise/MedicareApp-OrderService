package com.medicare.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.medicare.orderservice.entity.PurchaseOrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPurchaseOrder {

	private List<PurchaseItemDto> purchaseItemDto;
	
    private Integer customerId;

    private String shippingAddress;
    
    private String billingAddress;
    
    private String shippingMethod;
}
