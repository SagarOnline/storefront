package com.sagar.solutions.storefront.shoppingcounter;

import com.sagar.ddd.Aggregate;
import com.sagar.solutions.storefront.shoppingcart.ShoppingCartAggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShoppingCounterAggregate implements Aggregate {

    @NonNull
    private ShoppingCounter shoppingCounter;

    private List<Integer> inProcessShoppingCarts = new ArrayList<>();

    public ShoppingCartAggregate startShoppingCart(String forCustomer){
        return null;

    }

    public ShoppingCartAggregate getShoppingCart(Integer shoppingCartId){
        return null;
    }
}
