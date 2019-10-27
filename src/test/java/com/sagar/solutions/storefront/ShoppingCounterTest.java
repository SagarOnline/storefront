package com.sagar.solutions.storefront;

import com.sagar.solutions.storefront.domain.StoreFrontAggregate;
import com.sagar.solutions.storefront.domain.StoreFrontService;
import com.sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ShoppingCounterTest {

    @Autowired
    private StoreFrontService storeFrontService;

    @Test
    public void testRepository(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter("First");

        Optional<ShoppingCounterAggregate> firstCounter = storeFront.getShoppingCounter("First");
        assertTrue(firstCounter.isPresent());
    }

}
