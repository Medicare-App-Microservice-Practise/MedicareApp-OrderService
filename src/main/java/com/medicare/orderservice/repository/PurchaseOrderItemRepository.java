package com.medicare.orderservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicare.orderservice.entity.PurchaseOrderItem;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem,Integer> {

	@Query("SELECT p from PurchaseOrderItem p where p.purchaseOrder.purchaseOrderId=:purchaseOrderId")
	List<PurchaseOrderItem> getPurchaseOrderItem(@Param("purchaseOrderId") int purchaseOrderId);
	
}
