package sagar.solutions.storefront.domain.shoppingcart;

import java.util.concurrent.Executors;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
