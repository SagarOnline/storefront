package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.salestax.SalesTaxCalculator;
import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    private Integer orderItemId;

    private String productName;

    private BigDecimal orderQuantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;
}
