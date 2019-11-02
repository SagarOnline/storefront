package sagar.solutions.storefront.domain.shoppingcounter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.ddd.Command;

@RequiredArgsConstructor
@Getter
public class SetupNewShoppingCounterCommand implements Command {

    @NonNull
    private String counterName;

}
