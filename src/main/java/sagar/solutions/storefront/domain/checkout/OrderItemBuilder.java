package sagar.solutions.storefront.domain.checkout;

import sagar.solutions.storefront.domain.cost.Cost;
import sagar.solutions.storefront.domain.cost.SalesTaxCalculator;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.util.BeanUtil;

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
            salesTaxCalculator = BeanUtil.getBean(SalesTaxCalculator.class);
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(this.cartItem.getProduct().getProductName());
        orderItem.setOrderQuantity(this.cartItem.getQuantity());
        orderItem.setUnitPrice(this.cartItem.getProduct().getUnitPrice());

        BigDecimal totalSalesTax = this.salesTaxCalculator.calculateSalesTax(cartItem);

        Cost itemCost = new Cost(this.cartItem.getTotalPrice(), totalSalesTax);
        orderItem.setTotalCost(itemCost);

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
