package sagar.solutions.storefront.domain.inventorymanagement;

import org.springframework.data.repository.CrudRepository;
import sagar.solutions.storefront.domain.productcatalog.Product;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounter;

import java.util.Optional;

public interface ProductInventoryRepository extends CrudRepository<ProductInventory, Long> {

    public Optional<ProductInventory> findByProduct(Product product);


}
