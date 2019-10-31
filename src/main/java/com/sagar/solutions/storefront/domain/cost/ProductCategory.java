package com.sagar.solutions.storefront.domain.cost;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductCategory {
    CATEGORY_A ("Category A"), CATEGORY_B("Category B"), CATEGORY_C("Category C");

    @Getter
    @NonNull
    private String categoryName;


}
