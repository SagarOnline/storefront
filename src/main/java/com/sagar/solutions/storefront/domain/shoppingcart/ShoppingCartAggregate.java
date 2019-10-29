package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.solutions.storefront.domain.checkout.PurchaseOrder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderBuilder;
import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderRepository;
import com.sagar.solutions.storefront.util.BeanUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class ShoppingCartAggregate {

    @NonNull
    private ShoppingCart shoppingCart;


    public void addToCart(String productId, Integer quantity){

    }

    public void removeFromCart(String productId, Integer quantity){

    }

    public List<CartItem> getItemsInCart(){
        return null;
    }

    @Transactional
    public PurchaseOrderAggregate checkoutCart(){
        PurchaseOrder purchaseOrder = createPurchaseOrderFromShoppingCart();
        PurchaseOrderRepository purchaseOrderRepository = BeanUtil.getBean(PurchaseOrderRepository.class);
        purchaseOrderRepository.save(purchaseOrder);

        //update shopping cart entity
        this.shoppingCart.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        this.shoppingCart.setCartStatus(CartStatus.CHECKEDOUT);
        ShoppingCartRepository shoppingCartRepository = BeanUtil.getBean(ShoppingCartRepository.class);
        shoppingCartRepository.save(this.shoppingCart);
        return null;
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




}
