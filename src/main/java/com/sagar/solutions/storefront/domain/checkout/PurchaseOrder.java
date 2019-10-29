package com.sagar.solutions.storefront.domain.checkout;

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

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;

    @OneToMany
    private List<OrderItem> orderItems;

}


