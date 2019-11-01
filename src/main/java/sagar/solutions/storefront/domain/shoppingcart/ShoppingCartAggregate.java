package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderRepository;
import com.sagar.solutions.storefront.domain.product.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class ShoppingCartAggregate {

    @Autowired
    private PurchaseOrderAggregate.PurchaseOrderAggregateAggregateFactory purchaseOrderAggregateAggregateFactory;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @NonNull
    @Getter
    private ShoppingCart shoppingCart;

    @Transactional
    public void addToCart(Product product, BigDecimal quantity){
        this.shoppingCart.getCartItemList().add(new CartItem(product, quantity));

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