package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.salestax.SalesTaxCalculator;
import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;

import java.math.BigDecimal;

public class OrderItemBuilder {
    //TODO : Move this builder class into OrderItem class

    private CartItem cartItem;

    private SalesTaxCalculator salesTaxCalculator;


    public OrderItem build(){
        if(this.cartItem == null){
            throw new IllegalArgumentException("Cart Item is mandatory");
        }

        if(this.salesTaxCalculator == null){
            salesTaxCalculator = SalesTaxCalculator.getInstance();
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(this.cartItem.getProduct().getProductName());
        orderItem.setOrderQuantity(this.cartItem.getQuantity());
        orderItem.setUnitPrice(this.cartItem.getProduct().getUnitPrice());
        BigDecimal totalSalesTax = this.salesTaxCalculator.calculateSalesTax(cartItem);
        orderItem.setTotalSalesTax(totalSalesTax);
        orderItem.setTotalPrice(totalSalesTax.add(this.cartItem.getTotalPrice()));

        return orderItem;
    }

    public OrderItemBuilder cartItem(CartItem cartItem){
        this.cartItem = cartItem;
        return this;
    }

    public  OrderItemBuilder salesTaxCalculator(SalesTaxCalculator salesTaxCalculator){
        this.salesTaxCalculator = salesTaxCalculator;
        return this;
    }

}
