package com.sagar.solutions.storefront;


import com.sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import com.sagar.solutions.storefront.domain.productcatalog.Product;
import com.sagar.solutions.storefront.domain.salestax.ProductCategory;
import com.sagar.solutions.storefront.domain.salestax.ProductCategorySalesTax;
import com.sagar.solutions.storefront.domain.salestax.ProductCategorySalesTaxRepository;
import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PurchaseOrderBuilderIntegrationTest {

    @Autowired
    private ProductCategorySalesTaxRepository productCategorySalesTaxRepository;

    @Before
    public void setup(){

        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_A,
                new BigDecimal(10)));
        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_B,
                new BigDecimal(20)));
        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_C,
                new BigDecimal(0)));
    }

    @Test
    public void testPurchaseOrderBuilder(){
        String customerName = "Sagar";
        ShoppingCart shoppingCart = new ShoppingCart(customerName);

        Product iphone = new Product("iphone", ProductCategory.CATEGORY_A, new BigDecimal("60000"));
        CartItem iphoneItem = new CartItem(iphone, new BigDecimal(1));

        Product iphoneCase = new Product("iphone case", ProductCategory.CATEGORY_B, new BigDecimal("500"));
        CartItem iphoneCaseItem = new CartItem(iphone, new BigDecimal(1));

        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder();
        PurchaseOrder purchaseOrder = purchaseOrderBuilder.shoppingCart(shoppingCart)
                                    .cartItem(iphoneItem)
                                    .cartItem(iphoneCaseItem).build();

        assertNotNull(purchaseOrder);
        assertEquals(2, purchaseOrder.getOrderItems().size());
        assertEquals(customerName, purchaseOrder.getCustomerName());

        //TODO : Asserts on cost calcuations are pending.
        // Need to refactor a code in PurchaseOrderBuilder which sums the sales tax and gross price for each
        // OrderItem. This will enable writing asserts on cost calculations.
    }
}
