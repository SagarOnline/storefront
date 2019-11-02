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
import sagar.solutions.storefront.domain.productcatalog.Product;
import sagar.solutions.storefront.domain.productcatalog.ProductRepository;
import sagar.solutions.storefront.domain.scanning.Scanner;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounter;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;
import sagar.solutions.storefront.testdata.TestProduct;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ShoppingCounterIntegrationTest {

    @Autowired
    private ShoppingCounterAggregate.ShoppingCounterAggregateFactory shoppingCounterAggregateFactory;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setup(){
        productRepository.save(TestProduct.iphone);
    }

    @Test
    public void testScanValidProduct(){

        ShoppingCounterAggregate shoppingCounterAggregate = shoppingCounterAggregateFactory.getShoppingCounterAggregate(new ShoppingCounter("my counter", new Scanner(
                "LX500")));

        Optional<Product> product = shoppingCounterAggregate.scanProduct(TestProduct.iphone.getBarCode());
        assertTrue(product.isPresent());

        assertEquals(product.get().getProductName(), TestProduct.iphone.getProductName());
    }

    @Test
    public void testScanInvValidProduct(){

        ShoppingCounterAggregate shoppingCounterAggregate = shoppingCounterAggregateFactory.getShoppingCounterAggregate(new ShoppingCounter("my counter", new Scanner(
                "LX500")));

        Optional<Product> product = shoppingCounterAggregate.scanProduct("32434343434");
        assertFalse(product.isPresent());

    }



}
