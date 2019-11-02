package sagar.solutions.storefront.domain.shoppingcounter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sagar.solutions.storefront.domain.scanning.Scanner;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class ShoppingCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long counterId;

    @NonNull
    private String counterName;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Scanner scanner;

}
