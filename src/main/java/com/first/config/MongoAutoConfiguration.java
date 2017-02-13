package com.first.config;

import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.first.config.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;  
  
@Configuration  
@ConditionalOnClass(MongoClient.class)  
@EnableConfigurationProperties(MongoProperties.class)  
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")  
public class MongoAutoConfiguration {  
  
    @Autowired  
    private MongoProperties properties;  
  
    @Autowired(required = false)  
    private MongoClientOptions options;  
  
    @Autowired  
    private Environment environment;  
  
    private MongoClient mongo;  
  
    @PreDestroy  
    public void close() {  
        if (this.mongo != null) {  
            this.mongo.close();  
        }  
    }  
  
    @Bean  
    @ConditionalOnMissingBean  
    public MongoClient mongo() throws UnknownHostException {  
        this.mongo = this.properties.createMongoClient(this.options, this.environment);  
        return this.mongo;  
    }  
  
}
