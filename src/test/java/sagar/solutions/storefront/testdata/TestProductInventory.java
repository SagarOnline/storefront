package sagar.solutions.storefront.testdata;

import sagar.solutions.storefront.domain.cost.SalesTaxCategory;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;
import sagar.solutions.storefront.domain.productcatalog.Product;

import java.math.BigDecimal;

public class TestProductInventory {

    public static final ProductInventory iphoneInventory = new ProductInventory(TestProduct.iphone,
            new BigDecimal(10));

    public static final ProductInventory iphoneCaseInventory = new ProductInventory(TestProduct.iphoneCase,
            new BigDecimal(10));
}
