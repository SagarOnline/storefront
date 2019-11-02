package sagar.solutions.storefront;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sagar.solutions.storefront.domain.checkout.OrderItem;
import sagar.solutions.storefront.domain.checkout.OrderItemBuilder;
import sagar.solutions.storefront.domain.cost.Cost;
import sagar.solutions.storefront.domain.cost.SalesTaxCalculator;
import sagar.solutions.storefront.domain.shoppingcart.CartItem;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.testdata.TestProduct;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemBuilderTest {

    @Autowired
    private SalesTaxCalculator salesTaxCalculator;

    @Test
    public void testPurchaseOrderBuilder(){
        String customerName = "Sagar";
        ShoppingCart shoppingCart = new ShoppingCart(customerName);

        BigDecimal quantity = new BigDecimal(1);
        CartItem iphoneItem = new CartItem(TestProduct.iphone, quantity);


        OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
        OrderItem orderItem = orderItemBuilder.cartItem(iphoneItem).salesTaxCalculator(salesTaxCalculator).build();

        assertNotNull(orderItem);
        assertEquals(iphoneItem.getProduct().getProductName(), orderItem.getProductName());
        assertEquals(iphoneItem.getProduct().getUnitPrice(), orderItem.getUnitPrice());

        Cost totalCost = orderItem.getTotalCost();
        assertEquals(iphoneItem.getProduct().getUnitPrice().multiply(quantity), totalCost.getGrossAmount());

    }
}
