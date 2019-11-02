package sagar.solutions.storefront.domain.shoppingcart;

import sagar.solutions.storefront.domain.inventory.ProductInventory;
import sagar.solutions.storefront.util.BeanUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    private ProductInventory product;

    @NonNull
    private BigDecimal quantity;


    public BigDecimal getTotalPrice(){
        TotalCalculator totalCalculator = BeanUtil.getBean(TotalCalculator.class);
        return totalCalculator.calculate(this.product.getUnitPrice(), this.quantity);
    }

    @Component
    public static class TotalCalculator{

        public BigDecimal calculate(BigDecimal unitPrice, BigDecimal quantity){
            return quantity.multiply(unitPrice);
        }

    }
}
