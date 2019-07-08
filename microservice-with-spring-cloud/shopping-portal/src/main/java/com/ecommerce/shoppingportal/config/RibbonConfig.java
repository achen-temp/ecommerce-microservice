package com.ecommerce.shoppingportal.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Autowired
    IClientConfig config;

    @Bean
    public IPing ribbonPing(IClientConfig config){
        return new PingUrl();
    }

    public IRule ribbonRule(IClientConfig config){
        return new RoundRobinRule();
    }
}
