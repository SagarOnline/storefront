package com.sagar.solutions.storefront.domain.checkout;

import com.sagar.ddd.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PurchaseOrderAggregate implements Aggregate {

    @NonNull
    private  PurchaseOrder purchaseOrder;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }
}
