package sagar.solutions.storefront.domain.checkout;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Data;
import sagar.solutions.storefront.domain.cost.Cost;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    private String productName;

    private BigDecimal orderQuantity;

    private BigDecimal unitPrice;

    @Embedded
    private Cost totalCost;
}
