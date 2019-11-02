package sagar.solutions.storefront;


import sagar.solutions.storefront.domain.checkout.OrderItem;
import sagar.solutions.storefront.domain.checkout.OrderItemBuilder;
import sagar.solutions.storefront.domain.cost.*;
import sagar.solutions.storefront.domain.product.Product;
import sagar.solutions.storefront.domain.cost.*;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sagar.solutions.storefront.testdata.TestProduct;

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

        BigDecimal quantity = new BigDecimal(1);
        CartItem iphoneItem = new CartItem(TestProduct.iphone, quantity);


        OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
        OrderItem orderItem = orderItemBuilder.cartItem(iphoneItem).salesTaxCalculator(SalesTaxCalculator.getInstance()).build();

        assertNotNull(orderItem);
        assertEquals(iphoneItem.getProduct().getProductName(), orderItem.getProductName());
        assertEquals(iphoneItem.getProduct().getUnitPrice(), orderItem.getUnitPrice());

        Cost totalCost = orderItem.getTotalCost();
        assertEquals(iphoneItem.getProduct().getUnitPrice().multiply(quantity), totalCost.getGrossAmount());

    }
}
