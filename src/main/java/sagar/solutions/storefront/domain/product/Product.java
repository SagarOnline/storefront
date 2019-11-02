package sagar.solutions.storefront.domain.product;

import sagar.solutions.storefront.domain.cost.SalesTaxCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NonNull
    private String productName;

    @NonNull
    @Enumerated
    private SalesTaxCategory salesTaxProductCategory;

    @NonNull
    private BigDecimal unitPrice;

    @NonNull
    private BigDecimal availableStock;
}
