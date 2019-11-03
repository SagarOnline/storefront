package sagar.solutions.storefront.domain.checkout;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import sagar.ddd.Aggregate;

@RequiredArgsConstructor
public class PurchaseOrderAggregate implements Aggregate {

    @NonNull
    private final PurchaseOrder purchaseOrder;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    @Configuration
    public static class PurchaseOrderAggregateAggregateFactory{

        @Bean
        @Scope("prototype")
        public PurchaseOrderAggregate getPurchaseOrderAggregate(final PurchaseOrder purchaseOrder){
            return new PurchaseOrderAggregate(purchaseOrder);
        }
    }
}
