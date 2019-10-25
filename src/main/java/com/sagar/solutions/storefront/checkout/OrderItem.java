package com.sagar.solutions.storefront.checkout;

import java.math.BigDecimal;

public class OrderItem {

    private Integer orderItemId;

    private String productName;

    private Integer orderQuantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;
}
