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
import sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventoryRepository;
import sagar.solutions.storefront.domain.productcatalog.ProductRepository;
import sagar.solutions.storefront.domain.shoppingcart.InsufficientStockException;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import sagar.solutions.storefront.testdata.TestProduct;
import sagar.solutions.storefront.testdata.TestProductInventory;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CheckoutIntegrationTest {

    @Autowired
    private StoreFrontService storeFrontService;

    private static String SHOPPING_COUNTER_NAME = "MyStore";

    private static String CUSTOMER_NAME = "Sagar";

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ProductRepository productRepository;

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

        PurchaseOrderAggregate purchaseOrderAggregate = shoppingCartAggregate.checkoutCart();
        assertNotNull(purchaseOrderAggregate);

        PurchaseOrder purchaseOrder = purchaseOrderAggregate.getPurchaseOrder();
        assertNotNull(purchaseOrder);

        assertEquals(2, purchaseOrder.getOrderItems().size());


        assertEquals(ShoppingCart.CartStatus.CHECKEDOUT,
                    shoppingCartAggregate.getShoppingCart().getCartStatus());
        //TODO : add asserts to check Purchase Order Details

    }

    @Test(expected = InsufficientStockException.class)
    public void testInventoryCheckOnAddingItemsToShoppingCart() throws InsufficientStockException{
        ShoppingCartAggregate shoppingCartAggregate =
                storeFrontService.getStoreFront()
                        .getShoppingCounter(SHOPPING_COUNTER_NAME).get()
                        .startShoppingCart(CUSTOMER_NAME);

        // try adding more than available stock
        BigDecimal orderQuantity = TestProductInventory.iphoneInventory.getAvailableStock().add(new BigDecimal(1));
        shoppingCartAggregate.addToCart(TestProduct.iphone,
                orderQuantity);

    }
}
