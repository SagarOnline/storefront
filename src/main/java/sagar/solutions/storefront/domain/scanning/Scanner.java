package sagar.solutions.storefront.domain.scanning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Scanner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scannerId;

    @NonNull
    private String modelName;

}
