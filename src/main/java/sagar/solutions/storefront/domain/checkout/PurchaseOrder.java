package sagar.solutions.storefront.domain.checkout;

import java.util.List;
import javax.persistence.*;

import lombok.Data;
import sagar.solutions.storefront.domain.cost.Cost;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrderId;

    private String customerName;

    private Cost totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}


