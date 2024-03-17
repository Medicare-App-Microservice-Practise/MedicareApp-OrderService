package com.medicare.orderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_purchaseOrder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer purchaseOrderId;
    
    private Integer customerId;
    
    private String status;
    
    private String shippingAddress;
    
    private String billingAddress;
    
    private String shippingMethod;
    
    private Boolean isActive;
    
	@Column(nullable=false)
	private LocalDateTime createdDateTime;
	
	@Column(nullable=false)
	private LocalDateTime updatedDateTime;
	
	@PrePersist
	protected void onCreate()
	{
		createdDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
		isActive = true;
	}
	
	@PreUpdate
	protected void onUpdate()
	{
		updatedDateTime = LocalDateTime.now();
	}
	
    @OneToMany(mappedBy="purchaseOrder", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<PurchaseOrderItem> purchaseOrderItems;
}
