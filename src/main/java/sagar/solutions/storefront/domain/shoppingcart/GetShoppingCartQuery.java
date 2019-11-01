package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.ddd.Query;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetShoppingCartQuery implements Query {

    @NonNull
    private Long shopingCartId;
}
