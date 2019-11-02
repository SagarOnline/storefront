package sagar.solutions.storefront.domain.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class ShoppingCart {

    public static enum CartStatus {
        CREATED, CHECKEDOUT
    }

    public ShoppingCart(String customerName){
        this.customerName = customerName;
        cartStatus = CartStatus.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @Setter
    private String customerName;

    @Setter
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    @Setter
    private Long purchaseOrderId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItemList = new ArrayList<>();



}
