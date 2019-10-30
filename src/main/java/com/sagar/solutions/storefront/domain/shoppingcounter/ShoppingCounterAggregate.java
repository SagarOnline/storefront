package com.sagar.solutions.storefront.domain.shoppingcounter;

import com.sagar.ddd.Aggregate;
import com.sagar.solutions.storefront.domain.StoreFrontAggregate;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import com.sagar.solutions.storefront.domain.shoppingcart.ShoppingCartRepository;
import com.sagar.solutions.storefront.util.BeanUtil;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class ShoppingCounterAggregate implements Aggregate {

    @Autowired
    private ShoppingCartAggregate.ShoppingCartAggregateAggregateFactory shoppingCartAggregateAggregateFactory;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @NonNull
    private ShoppingCounter shoppingCounter;

    //TODO : Need to find good name for this method
    @Transactional
    public ShoppingCartAggregate startShoppingCart(String forCustomer){
        ShoppingCart cart = new ShoppingCart(forCustomer);
        this.shoppingCartRepository.save(cart);

        ShoppingCartAggregate shoppingCartAggregate =
                shoppingCartAggregateAggregateFactory.getShoppingCartAggregate(cart);
        return shoppingCartAggregate;
    }

    public Optional<ShoppingCartAggregate>  getShoppingCart(Long shoppingCartId){
        Optional<ShoppingCart> cart = this.shoppingCartRepository.findById(shoppingCartId);
        return cart.map(value -> shoppingCartAggregateAggregateFactory.getShoppingCartAggregate(value));
    }


    @Configuration
    public static class ShoppingCounterAggregateFactory{

        @Bean
        @Scope("prototype")
        public ShoppingCounterAggregate getShoppingCounterAggregate(ShoppingCounter shoppingCounter){
            return new ShoppingCounterAggregate(shoppingCounter);
        }
    }
}
