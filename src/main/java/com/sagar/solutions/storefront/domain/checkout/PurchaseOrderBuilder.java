package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.salestax.Cost;
import com.sagar.solutions.storefront.domain.salestax.SalesTaxCalculator;
import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBuilder {

    //TODO : Move this builder class into PurchaseOrder class
    private ShoppingCart shoppingCart;

    private List<CartItem> cartItemList = new ArrayList<>();

    public PurchaseOrder build(){
        if(shoppingCart == null){
            throw new IllegalArgumentException("Shopping is required as Purchase Order can be created from existing " +
                    "shopping cart");
        }
        // TODO : Add more validations before building PurchaseOrder. Should throw IllegalArgumentExecption

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCustomerName(this.shoppingCart.getCustomerName());

        SalesTaxCalculator salesTaxCalculator = SalesTaxCalculator.getInstance();
        List<OrderItem> orderItemList = getOrderItems(salesTaxCalculator);
        purchaseOrder.setOrderItems(orderItemList);

        BigDecimal totalSalesTax = calculateTotalSalesTax(orderItemList);
        BigDecimal subTotalPrice = calculateSubtotalPrice(orderItemList);
        Cost totalPrice = new Cost(subTotalPrice, totalSalesTax);
        purchaseOrder.setTotalPrice(totalPrice);

        return purchaseOrder;
    }

    private BigDecimal calculateTotalSalesTax(List<OrderItem> orderItemList) {
        BigDecimal totalSalesTax = new BigDecimal(0);
        orderItemList.forEach(item -> totalSalesTax.add(item.getTotalCost().getSalesTax()));
        return totalSalesTax;
    }

    private BigDecimal calculateTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal totalPrice = new BigDecimal(0);
        orderItemList.forEach(item -> totalPrice.add(item.getTotalCost().getTotal()));
        return totalPrice;
    }

    private BigDecimal calculateSubtotalPrice(List<OrderItem> orderItemList) {
        BigDecimal totalPrice = new BigDecimal(0);
        orderItemList.forEach(item -> totalPrice.add(item.getTotalCost().getTotal()));
        return totalPrice;
    }

    private List<OrderItem> getOrderItems(SalesTaxCalculator salesTaxCalculator) {
        List<OrderItem> orderItemList = new ArrayList<>();
        cartItemList.forEach(cartItem -> {
            OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
            OrderItem orderItem = orderItemBuilder.cartItem(cartItem).salesTaxCalculator(salesTaxCalculator).build();
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }

    public PurchaseOrderBuilder shoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
        return this;
    }

    public PurchaseOrderBuilder cartItem(CartItem cartItem){
        this.cartItemList.add(cartItem);
        return this;
    }


}
