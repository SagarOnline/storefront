package sagar.solutions.storefront.domain.shoppingcart;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.solutions.storefront.domain.productcatalog.Product;

@RequiredArgsConstructor
@Getter
public class AddItemToShoppingCartCommand {

    @NonNull
    private Long shoppingCounterId;

    @NonNull
    private Long shoppingCartId;

    @NonNull
    private Long productId;

    @NonNull
    private BigDecimal quantity;
}
