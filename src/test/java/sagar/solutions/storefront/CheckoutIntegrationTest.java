package sagar.solutions.storefront;

import sagar.solutions.storefront.domain.StoreFrontAggregate;
import sagar.solutions.storefront.domain.StoreFrontService;
import sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import sagar.solutions.storefront.domain.cost.ProductCategory;
import sagar.solutions.storefront.domain.cost.ProductCategorySalesTax;
import sagar.solutions.storefront.domain.cost.ProductCategorySalesTaxRepository;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CheckoutIntegrationTest {

    @Autowired
    private StoreFrontService storeFrontService;

    @Autowired
    private ProductCategorySalesTaxRepository productCategorySalesTaxRepository;

    private static String SHOPPING_COUNTER_NAME = "MyStore";

    private static String CUSTOMER_NAME = "Sagar";

    @Before
    public void setup(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter(SHOPPING_COUNTER_NAME);

        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_A,
                new BigDecimal(10)));
        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_B,
                new BigDecimal(20)));
        productCategorySalesTaxRepository.save(new ProductCategorySalesTax(ProductCategory.CATEGORY_C,
                new BigDecimal(0)));
    }

    @Test
    public void testAddingItemsToShoppingCart(){
        ShoppingCartAggregate shoppingCartAggregate =
                storeFrontService.getStoreFront()
                        .getShoppingCounter(SHOPPING_COUNTER_NAME).get()
                        .startShoppingCart(CUSTOMER_NAME);
        shoppingCartAggregate.addToCart(TestProduct.iphone, new BigDecimal(1));

        shoppingCartAggregate.addToCart(TestProduct.iphoneCase, new BigDecimal(2));

        PurchaseOrderAggregate purchaseOrderAggregate = shoppingCartAggregate.checkoutCart();
        assertNotNull(purchaseOrderAggregate);

        PurchaseOrder purchaseOrder = purchaseOrderAggregate.getPurchaseOrder();
        assertNotNull(purchaseOrder);

        assertEquals(2, purchaseOrder.getOrderItems().size());


        assertEquals(ShoppingCart.CartStatus.CHECKEDOUT,
                    shoppingCartAggregate.getShoppingCart().getCartStatus());
        //TODO : add asserts to check Purchase Order Details

    }
}
