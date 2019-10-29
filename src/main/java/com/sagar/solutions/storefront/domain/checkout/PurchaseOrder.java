package com.sagar.solutions.storefront.domain.checkout;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    private Long purchaseOrderId;

    private String customerName;

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;

    private List<OrderItem> orderItems = new ArrayList<>();

}


