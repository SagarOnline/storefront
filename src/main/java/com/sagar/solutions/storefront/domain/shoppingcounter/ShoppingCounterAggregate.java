package com.sagar.solutions.storefront.domain.shoppingcounter;

import com.sagar.ddd.Aggregate;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ShoppingCounterAggregate implements Aggregate {

    @NonNull
    private ShoppingCounter shoppingCounter;

    public ShoppingCartAggregate startShoppingCart(String forCustomer){
        return null;

    }

    public ShoppingCartAggregate getShoppingCart(Integer shoppingCartId){
        return null;
    }
}
