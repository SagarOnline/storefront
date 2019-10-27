package com.sagar.solutions.storefront.domain.shoppingcounter;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
