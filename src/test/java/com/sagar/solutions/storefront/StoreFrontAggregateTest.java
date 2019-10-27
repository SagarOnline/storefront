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
public class StoreFrontAggregateTest {

    @Autowired
    private StoreFrontService storeFrontService;

    @Test
    public void testSetupNewCounterAndGetIt(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter("First");

        Optional<ShoppingCounterAggregate> firstCounter = storeFront.getShoppingCounter("First");
        assertTrue(firstCounter.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingUpDuplicateCounter(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
        storeFront.setupNewShoppingCounter("Counter1");
        storeFront.setupNewShoppingCounter("Counter1");

    }

    @Test
    public void testGetNonExistingShoppingCounter(){
        StoreFrontAggregate storeFront = storeFrontService.getStoreFront();

        Optional<ShoppingCounterAggregate> firstCounter = storeFront.getShoppingCounter("Invalid Counter");
        assertFalse(firstCounter.isPresent());
    }

}
