package com.sagar.solutions.storefront.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class StoreFrontService {



    /**
     * Returns the StoreFront instance which represents the Online Store.
     * @return
     */
    @Bean
    @Scope("prototype")
    public StoreFrontAggregate getStoreFront(){
        return new StoreFrontAggregate();
    }

}
