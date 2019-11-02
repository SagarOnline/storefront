package sagar.solutions.storefront.domain.shoppingcounter;

import sagar.ddd.Aggregate;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;
import sagar.solutions.storefront.domain.productcatalog.Product;
import sagar.solutions.storefront.domain.productcatalog.ProductRepository;
import sagar.solutions.storefront.domain.scanning.ScannerAggregate;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCart;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCartAggregate;
import sagar.solutions.storefront.domain.shoppingcart.ShoppingCartRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class ShoppingCounterAggregate implements Aggregate {

    @Autowired
    private ShoppingCartAggregate.ShoppingCartAggregateAggregateFactory shoppingCartAggregateAggregateFactory;

    @Autowired
    private ScannerAggregate.ScannerAggregateFactory scannerAggregateFactory;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

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

    public Optional<Product>  scanProduct(String barCode){
        Optional<Product> product = productRepository.findByBarCode(barCode);
        return  product;
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
