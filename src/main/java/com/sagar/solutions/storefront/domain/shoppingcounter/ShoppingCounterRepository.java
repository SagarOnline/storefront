package com.sagar.solutions.storefront.domain.shoppingcounter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ShoppingCounterRepository extends CrudRepository<ShoppingCounter, Long> {

    public ShoppingCounter findByCounterName(String counterName);
}
