package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.solutions.storefront.domain.checkout.PurchaseOrderAggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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

    public PurchaseOrderAggregate checkoutCart(){
        return null;
    }

    public PurchaseOrderAggregate getPurchaseOrder(){
        return null;
    }




}
