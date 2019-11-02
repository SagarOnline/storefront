package sagar.solutions.storefront.domain.shoppingcounter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingCounterRepository extends CrudRepository<ShoppingCounter, Long> {

    public Optional<ShoppingCounter> findByCounterName(String counterName);
}
