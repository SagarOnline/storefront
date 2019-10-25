package com.sagar.solutions.storefront.checkout;

import com.sagar.ddd.Aggregate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PurchaseOrderAggregate implements Aggregate {

    @NonNull
    private  PurchaseOrder purchaseOrder;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }
}
