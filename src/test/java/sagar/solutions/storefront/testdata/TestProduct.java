package sagar.solutions.storefront.testdata;

import sagar.solutions.storefront.domain.cost.SalesTaxCategory;
import sagar.solutions.storefront.domain.inventory.Product;

import java.math.BigDecimal;

public class TestProduct {

    public static final Product iphone = new Product("iPhone 7 64 GB", SalesTaxCategory.CATEGORY_A,
            new BigDecimal(50000.00), new BigDecimal(10));

    public static final Product iphoneCase = new Product("iPhone 7 Case", SalesTaxCategory.CATEGORY_B,
            new BigDecimal(200.00), new BigDecimal(10));
}
