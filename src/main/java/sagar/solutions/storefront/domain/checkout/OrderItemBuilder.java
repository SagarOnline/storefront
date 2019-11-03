package sagar.solutions.storefront.domain.checkout;

import java.math.BigDecimal;

import sagar.solutions.storefront.domain.cost.Cost;
import sagar.solutions.storefront.domain.cost.SalesTaxCalculator;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.util.BeanUtil;

public final class OrderItemBuilder {
    //TODO : Move this builder class into OrderItem class

    private CartItem cartItem;

    private SalesTaxCalculator salesTaxCalculator;


    public OrderItem build() {
        if (this.cartItem == null) {
            throw new IllegalArgumentException("Cart Item is mandatory");
        }

        if (this.salesTaxCalculator == null) {
            salesTaxCalculator = BeanUtil.getBean(SalesTaxCalculator.class);
        }

        final OrderItem orderItem = new OrderItem();
        orderItem.setProductName(this.cartItem.getProduct().getProductName());
        orderItem.setOrderQuantity(this.cartItem.getQuantity());
        orderItem.setUnitPrice(this.cartItem.getProduct().getUnitPrice());

        final BigDecimal totalSalesTax = this.salesTaxCalculator.calculateSalesTax(cartItem);

        final Cost itemCost = new Cost(this.cartItem.getTotalPrice(), totalSalesTax);
        orderItem.setTotalCost(itemCost);

        return orderItem;
    }

    public OrderItemBuilder cartItem(final CartItem cartItem) {
        this.cartItem = cartItem;
        return this;
    }

    public OrderItemBuilder salesTaxCalculator(final SalesTaxCalculator salesTaxCalculator) {
        this.salesTaxCalculator = salesTaxCalculator;
        return this;
    }

}
