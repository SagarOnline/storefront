package com.sagar.solutions.storefront;


import com.sagar.solutions.storefront.domain.checkout.OrderItem;
import com.sagar.solutions.storefront.domain.checkout.OrderItemBuilder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import com.sagar.solutions.storefront.domain.productcatalog.Product;
import com.sagar.solutions.storefront.domain.salestax.*;
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
public class OrderItemBuilderIntegrationTest {

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

        String productName = "iphone";
        BigDecimal unitPrice = new BigDecimal("60000");
        Product iphone = new Product(productName, ProductCategory.CATEGORY_A, unitPrice);
        BigDecimal quantity = new BigDecimal(1);
        CartItem iphoneItem = new CartItem(iphone, quantity);


        OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
        OrderItem orderItem = orderItemBuilder.cartItem(iphoneItem).salesTaxCalculator(SalesTaxCalculator.getInstance()).build();

        assertNotNull(orderItem);
        assertEquals(productName, orderItem.getProductName());
        assertEquals(unitPrice, orderItem.getUnitPrice());

        Cost totalCost = orderItem.getTotalCost();
        assertEquals(unitPrice.multiply(quantity), totalCost.getGrossAmount());

    }
}
