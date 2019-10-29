package com.sagar.solutions.storefront.domain.salestax;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class ProductCategorySalesTax {

    private Long productCategoryId;

    @Enumerated(EnumType.ORDINAL)
    private ProductCategory productCategoryCode;

    private String productCategoryName;

    private BigDecimal salesTaxApplicable;

}
