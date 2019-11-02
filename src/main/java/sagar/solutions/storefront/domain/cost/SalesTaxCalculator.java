package sagar.solutions.storefront.domain.cost;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * This class have method {@link #calculateSalesTax(CartItem)} to calculate the Sales Tax on the Product Item added
 * in a Shopping Cart.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class SalesTaxCalculator {

    public BigDecimal calculateSalesTax(CartItem cartItem){
        @NonNull SalesTaxCategory salesTaxProductCategory = cartItem.getProduct().getSalesTaxProductCategory();
        BigDecimal salesTax = cartItem.getTotalPrice().multiply(salesTaxProductCategory.getSalesTaxApplicable()).divide(new BigDecimal(100));
        return salesTax;
    }
}
