package com.sagar.solutions.storefront.domain.checkout;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrder {

    private Integer purchaseOrderId;

    private String customerName;

    private String shoppingCounterId;

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;

}
