package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.cost.Cost;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    private String productName;

    private BigDecimal orderQuantity;

    private BigDecimal unitPrice;

    @Embedded
    private Cost totalCost;
}
