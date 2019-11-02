package sagar.solutions.storefront.domain.shoppingcart;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import sagar.solutions.storefront.domain.checkout.PurchaseOrderRepository;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventoryRepository;
import sagar.solutions.storefront.domain.productcatalog.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShoppingCartAggregate {

    @Autowired
    private PurchaseOrderAggregate.PurchaseOrderAggregateAggregateFactory purchaseOrderAggregateAggregateFactory;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @NonNull
    @Getter
    private ShoppingCart shoppingCart;

    @Transactional
    public void addToCart(Product product, BigDecimal quantity) throws InsufficientStockException{
        boolean isSufficientStockAvailable = isSufficientStockAvailable(product, quantity);
        if(isSufficientStockAvailable){
            this.shoppingCart.getCartItemList().add(new CartItem(product, quantity));
        }else{
            throw new InsufficientStockException("Not sufficient Stock available to match the order ");
        }

    }

    private boolean isSufficientStockAvailable(Product product, BigDecimal quantity) {
        Optional<ProductInventory> productInventory = productInventoryRepository.findByProduct(product);
        return productInventory.filter(value -> value.getAvailableStock().compareTo(quantity) == 1).isPresent();
    }

    public void removeFromCart(String productId, Integer quantity){
        //TODO : Implement this feature
    }

    public List<CartItem> getItemsInCart(){
        return shoppingCart.getCartItemList();
    }

    @Transactional
    public PurchaseOrderAggregate checkoutCart(){
        PurchaseOrder purchaseOrder = createPurchaseOrderFromShoppingCart();
        this.purchaseOrderRepository.save(purchaseOrder);

        //update shopping cart entity
        this.shoppingCart.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        this.shoppingCart.setCartStatus(ShoppingCart.CartStatus.CHECKEDOUT);
        this.shoppingCartRepository.save(this.shoppingCart);
        return purchaseOrderAggregateAggregateFactory.getPurchaseOrderAggregate(purchaseOrder);
    }

    private PurchaseOrder createPurchaseOrderFromShoppingCart() {
        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder();
        purchaseOrderBuilder.shoppingCart(shoppingCart);
        List<CartItem> itemsInCart = getItemsInCart();
        itemsInCart.forEach(cartItem -> purchaseOrderBuilder.cartItem(cartItem));
        return purchaseOrderBuilder.build();
    }

    public PurchaseOrderAggregate getPurchaseOrder(){
        return null;
    }


    @Configuration
    public static class ShoppingCartAggregateAggregateFactory{

        @Bean
        @Scope("prototype")
        public ShoppingCartAggregate getShoppingCartAggregate(ShoppingCart shoppingCart){
            return new ShoppingCartAggregate(shoppingCart);
        }
    }


}
