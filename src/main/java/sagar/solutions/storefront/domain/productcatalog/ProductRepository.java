package sagar.solutions.storefront.domain.productcatalog;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    public Optional<Product> findByBarCode(String barCode);


}
