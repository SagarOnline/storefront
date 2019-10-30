package com.sagar.solutions.storefront.domain;

import com.sagar.ddd.Aggregate;
import com.sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounter;
import com.sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;
import com.sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterRepository;
import com.sagar.solutions.storefront.util.BeanUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Getter
@Configuration
public class StoreFrontAggregate implements Aggregate {

    private String name = "MyShop";

    @Autowired
    private ShoppingCounterRepository shoppingCounterRepository;


    @Transactional
    public void setupNewShoppingCounter(String name) {

        Optional<ShoppingCounter> shoppingCounter = shoppingCounterRepository.findByCounterName(name);
        if (shoppingCounter.isPresent()) {
            throw new IllegalArgumentException("Shopping Counter with name " + name + " already exists. Please setup a" +
                    " counter with new Name");
        } else {
            shoppingCounterRepository.save(new ShoppingCounter(name));
        }

    }

    @Bean
    @Scope("prototype")
    public Optional<ShoppingCounterAggregate> getShoppingCounter(String counterName) {
        Optional<ShoppingCounter> shoppingCounter = shoppingCounterRepository.findByCounterName(counterName);
        Optional<ShoppingCounterAggregate> shoppingCounterAggregate = shoppingCounter.map(value -> {
            return new ShoppingCounterAggregate(value);
        });
        return shoppingCounterAggregate;
    }

}
