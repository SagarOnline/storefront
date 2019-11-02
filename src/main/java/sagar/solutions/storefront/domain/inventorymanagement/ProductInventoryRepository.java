package sagar.solutions.storefront.domain.inventorymanagement;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import sagar.solutions.storefront.domain.productcatalog.Product;

public interface ProductInventoryRepository extends CrudRepository<ProductInventory, Long> {

    public Optional<ProductInventory> findByProduct(Product product);


}
