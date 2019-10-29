package com.sagar.solutions.storefront.domain.shoppingcounter;

import com.sagar.ddd.Aggregate;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartRepository;
import com.sagar.solutions.storefront.util.BeanUtil;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class ShoppingCounterAggregate implements Aggregate {

    @NonNull
    private ShoppingCounter shoppingCounter;

    //TODO : Need to find good name for this method
    @Transactional
    public ShoppingCartAggregate startShoppingCart(String forCustomer){
        ShoppingCart cart = new ShoppingCart(forCustomer);
        ShoppingCartRepository shoppingCartRepository = getShoppingCartRepository();
        shoppingCartRepository.save(cart);

        ShoppingCartAggregate shoppingCartAggregate = new ShoppingCartAggregate(cart);
        return shoppingCartAggregate;
    }

    private ShoppingCartRepository getShoppingCartRepository() {
        return BeanUtil.getBean(ShoppingCartRepository.class);
    }

    public Optional<ShoppingCartAggregate>  getShoppingCart(Long shoppingCartId){
        ShoppingCartRepository shoppingCartRepository = getShoppingCartRepository();
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(shoppingCartId);
        return cart.map(value -> new ShoppingCartAggregate(value));
    }
}
