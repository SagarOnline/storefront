package sagar.solutions.storefront;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sagar.solutions.storefront.domain.StoreFrontAggregate;
import sagar.solutions.storefront.domain.StoreFrontService;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventoryRepository;
import sagar.solutions.storefront.domain.productcatalog.ProductRepository;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.domain.shoppingcart.InsufficientStockException;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;
import sagar.solutions.storefront.testdata.TestProduct;
import sagar.solutions.storefront.testdata.TestProductInventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShoppingCartIntegrationTest {

    @Autowired
    private StoreFrontService storeFrontService;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private static String SHOPPING_COUNTER_NAME = "MyStore";

    private static String CUSTOMER_NAME = "Sagar";

    @Before
    public void setup(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter(SHOPPING_COUNTER_NAME);

        productRepository.save(TestProduct.iphone);
        productRepository.save(TestProduct.iphoneCase);

        productInventoryRepository.save(TestProductInventory.iphoneInventory);
        productInventoryRepository.save(TestProductInventory.iphoneCaseInventory);
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

    @Test
    public void testAddingItemsToShoppingCart(){
        ShoppingCartAggregate shoppingCartAggregate =
                storeFrontService.getStoreFront()
                        .getShoppingCounter(SHOPPING_COUNTER_NAME).get()
                        .startShoppingCart(CUSTOMER_NAME);
        try {
            shoppingCartAggregate.addToCart(TestProduct.iphone, new BigDecimal(1));
            shoppingCartAggregate.addToCart(TestProduct.iphoneCase, new BigDecimal(2));
        } catch (InsufficientStockException e) {
            fail("Inventory check failed while adding a product in shopping cart");
        }
        //shoppingCartAggregate.addToCart("iPhone 7 Case", 2);
        List<CartItem> itemsInCart = shoppingCartAggregate.getItemsInCart();
        assertEquals(2, itemsInCart.size());
        //TODO : add more asserts to check results

    }

    //TODO : add tests for get invalid Shooping cart
}
