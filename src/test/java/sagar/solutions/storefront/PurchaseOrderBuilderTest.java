package sagar.solutions.storefront;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.testdata.TestProduct;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderBuilderTest {



    @Test
    public void testPurchaseOrderBuilder(){
        String customerName = "Sagar";
        ShoppingCart shoppingCart = new ShoppingCart(customerName);

        CartItem iphoneItem = new CartItem(TestProduct.iphone, new BigDecimal(1));

        CartItem iphoneCaseItem = new CartItem(TestProduct.iphoneCase, new BigDecimal(1));

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
