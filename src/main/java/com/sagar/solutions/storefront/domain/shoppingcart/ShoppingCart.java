package com.sagar.solutions.storefront.domain.shoppingcart;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ShoppingCart {

    public static enum CartStatus {
        CREATED, CHECKEDOUT
    }

    public ShoppingCart(String customerName){
        this.customerName = customerName;
        cartStatus = CartStatus.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @Setter
    private String customerName;

    @Setter
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    @Setter
    private Long purchaseOrderId;



}
