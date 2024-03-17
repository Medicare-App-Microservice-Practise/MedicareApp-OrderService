package com.medicare.orderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_purchaseOrderItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer purchaseOrderItemId;
	
	private Integer productId;
    
    private String productName;
    
    private Integer quantity;
    
    private BigDecimal unitPrice;
    
    private BigDecimal totalPrice;
    
    @ManyToOne
    @JoinColumn(name="purchaseOrderId")
    @JsonBackReference
    private PurchaseOrder purchaseOrder;
}
