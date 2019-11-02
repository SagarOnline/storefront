package sagar.solutions.storefront.domain.shoppingcart;

import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class AddItemToShoppingCartCommand {

    @NonNull
    private ProductInventory product;

    @NonNull
    private BigDecimal quantity;
}
