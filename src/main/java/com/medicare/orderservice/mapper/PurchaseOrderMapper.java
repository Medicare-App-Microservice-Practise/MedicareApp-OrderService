package com.medicare.orderservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.medicare.orderservice.dto.PurchaseItemDto;
import com.medicare.orderservice.dto.PurchaseItemsandOrderDto;
import com.medicare.orderservice.dto.PurchaseOrderDto;
import com.medicare.orderservice.dto.RequestPurchaseOrder;
import com.medicare.orderservice.dto.ResponsePurchaseOrder;
import com.medicare.orderservice.entity.PurchaseOrder;
import com.medicare.orderservice.entity.PurchaseOrderItem;

@Mapper(componentModel="spring")
public interface PurchaseOrderMapper {

	public PurchaseOrder requestPurchaseToPurchase (RequestPurchaseOrder reqPurchaseOrderDto);
	
	public List<PurchaseOrder> ListRequestPurchaseToPurchase (List<RequestPurchaseOrder> reqPurchaseOrderDto);
	
	public ResponsePurchaseOrder purchaseToResponsePurchase (PurchaseOrder purchaseOrder);
	
	public List<ResponsePurchaseOrder> ListPurchaseToResponsePurchase (List<PurchaseOrder> purchaseOrder);
	
	public List<PurchaseOrderItem> ListPuchaseDtoToPurchaseOrderItem (List<PurchaseItemDto> purchaseOrderDto);
	
	public List<PurchaseItemDto> ListPuchaseOrderItemToPurchaseODto (List<PurchaseOrderItem> purchaseOrderItem);

	public List<PurchaseOrderDto> ListpurchaseOrderToPurchaseOrderDto (List<PurchaseOrder> purchaseOrder);
	
	public List<PurchaseItemsandOrderDto> mapPurchaseOrder (List<PurchaseOrderItem> purchaseOrderItem);
}
