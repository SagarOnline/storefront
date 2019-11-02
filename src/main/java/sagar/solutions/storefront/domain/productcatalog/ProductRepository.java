package sagar.solutions.storefront.domain.productcatalog;

import org.springframework.data.repository.CrudRepository;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    public Optional<Product> findByBarCode(String barCode);


}
