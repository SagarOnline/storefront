package sagar.solutions.storefront.domain.cost;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Value object represents a Cost attributes associated with any purchased item. It have fields representing costs such
 * as Gross Price, Tax, Freight charges, Discount, etc. It also have method which calculates Total Cost.
 */
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Embeddable
public class Cost {

    @NonNull
    private BigDecimal grossAmount;

    @NonNull
    private BigDecimal salesTax;

    public BigDecimal getTotal(){
        return grossAmount.add(salesTax);
    }

}
