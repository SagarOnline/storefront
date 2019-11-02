package sagar.solutions.storefront.testdata;

import sagar.solutions.storefront.domain.cost.SalesTaxCategory;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;
import sagar.solutions.storefront.domain.productcatalog.Product;

import java.math.BigDecimal;

public class TestProduct {

    public static final Product iphone = new Product("iPhone 7 64 GB",
            "001122334455667",
            SalesTaxCategory.CATEGORY_A,
            new BigDecimal(50000.00));

    public static final Product iphoneCase = new Product("iPhone 7 Case",
            "001122334455669",
            SalesTaxCategory.CATEGORY_B,
            new BigDecimal(200.00));
}
