package sagar.solutions.storefront.domain.shoppingcart;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
