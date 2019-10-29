package com.sagar.solutions.storefront.domain.salestax;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class ProductCategorySalesTax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCategoryId;

    @Enumerated(EnumType.ORDINAL)
    private ProductCategory productCategoryCode;

    private String productCategoryName;

    private BigDecimal salesTaxApplicable;

}
