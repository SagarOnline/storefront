package sagar.solutions.storefront.domain.scanning;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import sagar.ddd.Aggregate;
import sagar.solutions.storefront.domain.product.Product;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounter;
import sagar.solutions.storefront.domain.shoppingcounter.ShoppingCounterAggregate;

import java.util.Optional;

@RequiredArgsConstructor
public class ScannerAggregate implements Aggregate {

    @NonNull
    private  Scanner scanner;

    public Optional<Product> scanProduct(String scanCode){
        //TODO: implement it
        return null;
    }


    @Configuration
    public static class ScannerAggregateFactory{

        @Bean
        @Scope("prototype")
        public ScannerAggregate getScannerAggregate(Scanner scanner){
            return new ScannerAggregate(scanner);
        }
    }
}
