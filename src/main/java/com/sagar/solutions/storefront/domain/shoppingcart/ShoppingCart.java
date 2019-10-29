package com.sagar.solutions.storefront.domain.shoppingcart;

import lombok.Data;

@Data
public class ShoppingCart {

    private int cartId;

    private String customerName;

    private CartStatus cartStatus;

    private Long purchaseOrderId;



}
