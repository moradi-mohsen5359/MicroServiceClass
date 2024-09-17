package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductApplication.class, args);
    }

//    @Bean
//    RestClient restClientSimple() {
//        return RestClient.create("http://localhost:8080/api/v1");
//    }

//    @Bean
//    RestClient restClientWithRestTemplate() {
//        return RestClient.create(restTemplate());
//    }

//
    @Bean
    RestClient restClientWithBuilder() {

        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/v1")
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
