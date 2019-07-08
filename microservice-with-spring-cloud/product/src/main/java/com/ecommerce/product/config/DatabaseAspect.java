package com.ecommerce.product.config;

import com.netflix.discovery.converters.Auto;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Aspect
@Configuration
public class DatabaseAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseAspect.class);

    @Value("${product-datasource-configuration.url: URL NOT AVAILABLE}")
    private String url;

    private String username;

    @Autowired
    Environment env;


    @Before("execution(* com.ecommerce.product.repository.ProductRepository.*(..))")
    public void connectToDB(){
        this.username = env.getProperty("product-datasource-configuration.username");
        LOG.info(String.format(">>>>>>>>>Connect to Database: %s as User: %s", this.url, this.username));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
