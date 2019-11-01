package com.sagar.solutions.storefront.domain.shoppingcounter;

import com.sagar.ddd.Query;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetShoppingCounterQuery implements Query {

    @NonNull
    private String counterName;

}
