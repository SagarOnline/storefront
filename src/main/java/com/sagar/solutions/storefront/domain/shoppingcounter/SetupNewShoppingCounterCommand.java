package com.sagar.solutions.storefront.domain.shoppingcounter;

import com.sagar.ddd.Command;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SetupNewShoppingCounterCommand implements Command {

    @NonNull
    private String counterName;

}
