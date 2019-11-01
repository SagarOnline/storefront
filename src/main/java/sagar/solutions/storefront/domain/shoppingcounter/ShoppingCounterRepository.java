package sagar.solutions.storefront.domain.shoppingcounter;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShoppingCounterRepository extends CrudRepository<ShoppingCounter, Long> {

    public Optional<ShoppingCounter> findByCounterName(String counterName);
}
