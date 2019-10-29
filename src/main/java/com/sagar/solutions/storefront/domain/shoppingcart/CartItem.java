package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.solutions.storefront.domain.salestax.ProductCategory;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartItem {

    private Integer productId;

    private String productName;

    private BigDecimal quantity;

    private ProductCategory salesTaxProductCategory;

    private BigDecimal unitPrice;

    public BigDecimal getTotalPrice(){
        return quantity.multiply(unitPrice);
    }
}
