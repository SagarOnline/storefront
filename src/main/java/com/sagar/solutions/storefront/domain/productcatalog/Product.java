package com.sagar.solutions.storefront.domain.productcatalog;

import com.sagar.solutions.storefront.domain.salestax.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NonNull
    private String productName;

    @NonNull
    @Enumerated
    private ProductCategory salesTaxProductCategory;

    @NonNull
    private BigDecimal unitPrice;
}
