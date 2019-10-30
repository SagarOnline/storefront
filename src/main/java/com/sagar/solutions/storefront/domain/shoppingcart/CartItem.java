package com.sagar.solutions.storefront.domain.shoppingcart;

import com.sagar.solutions.storefront.domain.productcatalog.Product;
import com.sagar.solutions.storefront.domain.salestax.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @NonNull
    private BigDecimal quantity;


    public BigDecimal getTotalPrice(){
        return quantity.multiply(this.product.getUnitPrice());
    }
}
