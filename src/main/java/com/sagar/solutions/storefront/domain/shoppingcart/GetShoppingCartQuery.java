package com.sagar.solutions.storefront.domain.shoppingcart;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetShoppingCartQuery {

    @NonNull
    private Long shopingCartId;
}
