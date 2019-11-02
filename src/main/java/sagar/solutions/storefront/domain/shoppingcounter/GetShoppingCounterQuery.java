package sagar.solutions.storefront.domain.shoppingcounter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.ddd.Query;

@RequiredArgsConstructor
@Getter
public class GetShoppingCounterQuery implements Query {

    @NonNull
    private String counterName;

}
