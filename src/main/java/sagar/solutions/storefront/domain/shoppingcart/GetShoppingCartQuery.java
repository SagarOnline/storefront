package sagar.solutions.storefront.domain.shoppingcart;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.ddd.Query;

@RequiredArgsConstructor
@Getter
public class GetShoppingCartQuery implements Query {

    @NonNull
    private Long shoppingCounterId;

    @NonNull
    private Long shopingCartId;
}
