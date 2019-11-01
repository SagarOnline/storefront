package sagar.solutions.storefront;

import sagar.solutions.storefront.domain.cost.Cost;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CostTest {

    @Test
    public void testTotal(){
        BigDecimal grossAmount = new BigDecimal(100);
        BigDecimal salesTax = new BigDecimal("20");
        Cost cost = new Cost(grossAmount, salesTax);
        assertEquals(grossAmount.add(salesTax), cost.getTotal());
    }
}
