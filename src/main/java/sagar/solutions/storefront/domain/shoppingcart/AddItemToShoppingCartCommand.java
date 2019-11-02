package sagar.solutions.storefront.domain.shoppingcart;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.solutions.storefront.domain.inventorymanagement.ProductInventory;

@RequiredArgsConstructor
@Getter
public class AddItemToShoppingCartCommand {

    @NonNull
    private ProductInventory product;

    @NonNull
    private BigDecimal quantity;
}
