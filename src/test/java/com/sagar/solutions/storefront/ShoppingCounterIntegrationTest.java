package com.sagar.solutions.storefront;

import com.sagar.solutions.storefront.domain.StoreFrontAggregate;
import com.sagar.solutions.storefront.domain.StoreFrontService;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import com.sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;
import lombok.NonNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShoppingCounterIntegrationTest {

    @Autowired
    private StoreFrontService storeFrontService;

    private static String SHOPPING_COUNTER_NAME = "MyStore";

    private static String CUSTOMER_NAME = "Sagar";

    @Before
    public void setup(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter(SHOPPING_COUNTER_NAME);
    }

    @Test
    public void testStartShopping(){
        Optional<ShoppingCounterAggregate> shoppingCounter = storeFrontService.getStoreFront().getShoppingCounter(SHOPPING_COUNTER_NAME);
        assertTrue(shoppingCounter.isPresent());
        ShoppingCartAggregate shoppingCartAggregate = shoppingCounter.get().startShoppingCart(CUSTOMER_NAME);

        ShoppingCart shoppingCart = shoppingCartAggregate.getShoppingCart();
        assertNotNull(shoppingCart);
        assertEquals(ShoppingCart.CartStatus.CREATED, shoppingCart.getCartStatus());
        assertNotNull(shoppingCart.getCartId());
        assertNull(shoppingCart.getPurchaseOrderId());
        assertEquals(CUSTOMER_NAME, shoppingCart.getCustomerName());

    }


    @Test
    public void testGetValidShoppingCart(){
        Optional<ShoppingCounterAggregate> shoppingCounter = storeFrontService.getStoreFront().getShoppingCounter(SHOPPING_COUNTER_NAME);
        assertTrue(shoppingCounter.isPresent());
        ShoppingCartAggregate shoppingCartAggregate = shoppingCounter.get().startShoppingCart(CUSTOMER_NAME);

        ShoppingCart shoppingCart = shoppingCartAggregate.getShoppingCart();
        Long cartId = shoppingCart.getCartId();

        Optional<ShoppingCartAggregate> shoppingCart1 = shoppingCounter.get().getShoppingCart(cartId);
        assertTrue(shoppingCart1.isPresent());

    }
}
