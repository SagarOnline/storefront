package com.sagar.solutions.storefront.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
public class StoreFrontService {


    public StoreFrontAggregate getStoreFront(){
        return new StoreFrontAggregate();
    }
}
