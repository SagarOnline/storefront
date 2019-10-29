package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.solutions.storefront.domain.salestax.SalesTaxCalculator;
import com.sagar.solutions.storefront.domain.shoppingcart.CartItem;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private BigDecimal totalPrice;

    private BigDecimal totalSalesTax;
}
