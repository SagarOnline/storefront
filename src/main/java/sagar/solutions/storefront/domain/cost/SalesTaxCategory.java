package sagar.solutions.storefront.domain.cost;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SalesTaxCategory {
    CATEGORY_A ("Category A", new BigDecimal(10)),
    CATEGORY_B("Category B", new BigDecimal(20)),
    CATEGORY_C("Category C", new BigDecimal(0));

    @Getter
    @NonNull
    private String categoryName;

    @NonNull
    @Getter
    private BigDecimal salesTaxApplicable;


}
