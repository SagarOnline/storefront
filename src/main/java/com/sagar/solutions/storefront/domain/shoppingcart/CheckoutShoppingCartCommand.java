package com.sagar.solutions.storefront.domain.shoppingcart;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CheckoutShoppingCartCommand {

    @NonNull
    private Long shoppingCartId;
}
