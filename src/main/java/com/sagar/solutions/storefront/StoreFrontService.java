package com.sagar.solutions.storefront;

import com.sagar.solutions.storefront.shoppingcounter.ShoppingCounterAggregate;
import org.springframework.stereotype.Service;

@Service
public class StoreFrontService {

    public void setupNewShoppingCounter(String name){

    }

    public ShoppingCounterAggregate getShoppingCounter(String counterName){
        return null;
    }

    public ShoppingCounterAggregate getShoppingCounter(Integer counterId){
        return null;
    }
}
