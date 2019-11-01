package sagar.solutions.storefront.domain.checkout;

import sagar.ddd.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@RequiredArgsConstructor
public class PurchaseOrderAggregate implements Aggregate {

    @NonNull
    private  PurchaseOrder purchaseOrder;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }


    @Configuration
    public static class PurchaseOrderAggregateAggregateFactory{

        @Bean
        @Scope("prototype")
        public PurchaseOrderAggregate getPurchaseOrderAggregate(PurchaseOrder purchaseOrder){
            return new PurchaseOrderAggregate(purchaseOrder);
        }
    }
}
