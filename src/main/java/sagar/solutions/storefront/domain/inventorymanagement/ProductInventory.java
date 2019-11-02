package sagar.solutions.storefront.domain.inventorymanagement;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.solutions.storefront.domain.productcatalog.Product;

@Entity
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @OneToOne
    private Product product;

    @NonNull
    private BigDecimal availableStock;
}
