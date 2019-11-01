package sagar.solutions.storefront.domain.shoppingcart;

import sagar.ddd.Query;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetShoppingCartQuery implements Query {

    @NonNull
    private Long shopingCartId;
}
