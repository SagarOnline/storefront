package com.sagar.solutions.storefront.shoppingcart;

import lombok.Data;

@Data
public class ShoppingCart {

    private int cartId;

    private String customerName;

    private CartStatus cartStatus;

    private Integer purchaseOrderId;



}
