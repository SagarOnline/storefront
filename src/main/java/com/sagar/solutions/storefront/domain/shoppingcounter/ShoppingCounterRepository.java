package com.sagar.solutions.storefront.domain.shoppingcounter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ShoppingCounterRepository extends CrudRepository<ShoppingCounter, Long> {

    public Optional<ShoppingCounter> findByCounterName(String counterName);
}
