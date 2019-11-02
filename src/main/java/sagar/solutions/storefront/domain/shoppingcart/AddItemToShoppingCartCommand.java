package sagar.solutions.storefront.domain.shoppingcart;

import sagar.solutions.storefront.domain.inventory.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class AddItemToShoppingCartCommand {

    @NonNull
    private Product product;

    @NonNull
    private BigDecimal quantity;
}
