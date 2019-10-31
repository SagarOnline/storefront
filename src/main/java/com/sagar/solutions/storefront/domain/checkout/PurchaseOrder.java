package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.salestax.Cost;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrderId;

    private String customerName;

    private Cost totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}


