package com.sagar.solutions.storefront.domain.salestax;

import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;
import com.sagar.solutions.storefront.util.BeanUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This class have method {@link #calculateSalesTax(CartItem)} to calculate the Sales Tax on the Product Item added
 * in a Shopping Cart.
 * {@link #getInstance()} always returns a new instance . Same instance should be used for
 * calculating the sales tax for all the items a Shopping Cart to avoid fetching Sales Tax data for each calculation.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalesTaxCalculator {

    private Map<ProductCategory, BigDecimal> productCategorySalesTaxMap = new HashMap<>();

    public static SalesTaxCalculator getInstance(){
        ProductCategorySalesTaxRepository productCategorySalesTaxRepository = getProductCategorySalesTaxRepository();
        Iterable<ProductCategorySalesTax> productCategorySalesTaxes = productCategorySalesTaxRepository.findAll();

        SalesTaxCalculator salesTaxCalculator = new SalesTaxCalculator();
        productCategorySalesTaxes.forEach(entry -> salesTaxCalculator.productCategorySalesTaxMap.put(entry.getProductCategoryCode(),
                entry.getSalesTaxApplicable()));

        return salesTaxCalculator;
    }

    private static ProductCategorySalesTaxRepository getProductCategorySalesTaxRepository() {
        return BeanUtil.getBean(ProductCategorySalesTaxRepository.class);
    }

    public BigDecimal calculateSalesTax(CartItem cartItem){
        ProductCategory salesTaxProductCategory = cartItem.getProduct().getSalesTaxProductCategory();
        BigDecimal applicableTaxInPercentage = productCategorySalesTaxMap.get(salesTaxProductCategory);
        if(applicableTaxInPercentage == null){
            throw new IllegalArgumentException("Invalid Product Category. No sales tax data available for product " +
                    "category :"+ cartItem.getProduct().getSalesTaxProductCategory());
        }
        BigDecimal salesTax = cartItem.getTotalPrice().multiply(applicableTaxInPercentage).divide(new BigDecimal(100));
        return salesTax;
    }
}
