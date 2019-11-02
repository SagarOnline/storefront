package sagar.solutions.storefront.domain.productcatalog;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.solutions.storefront.domain.cost.SalesTaxCategory;

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
    private String barCode;

    @NonNull
    @Enumerated
    private SalesTaxCategory salesTaxProductCategory;

    @NonNull
    private BigDecimal unitPrice;
}
