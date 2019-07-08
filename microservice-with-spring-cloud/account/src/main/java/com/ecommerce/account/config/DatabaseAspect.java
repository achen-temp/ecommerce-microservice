package com.ecommerce.account.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
@ConfigurationProperties(prefix = "account-datasource-configuration")
public class DatabaseAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseAspect.class);

    private String url;
    private String username;
    private String password;

    @Before("execution(* com.ecommerce.account.repository.AccountDAO.*(..))")
    public void connectToDB(){
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
