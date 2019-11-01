package sagar.solutions.storefront.domain.cost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class ProductCategorySalesTax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCategoryId;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private ProductCategory productCategoryCode;

    @NonNull
    private BigDecimal salesTaxApplicable;

}
