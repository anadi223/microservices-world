package com.demo.microservices.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//! JUST TO DEMONSTRATE CIRCUIT BREAKER DOES NOT INTERFERE WITH OTHER CONTROLLER LOGIC
@RestController
public class CircuitBreakerController {


//    @GetMapping("/sample-api")
//    public String sampleApi(){
//        return "Sample-API";
//    }

//    @GetMapping("/sample-api")
//    public String sampleApiLetsMakeItFail(){
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
//        return forEntity.getBody();
//    }

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
@GetMapping("/sample-api")
@Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
public String sampleApiWithRetry(){
    logger.info("Sample API Call received");
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity
            ("http://localhost:8080/some-dummy-url",String.class);
    return forEntity.getBody();
}

public String hardcodedResponse(Exception ex){
    return "Fallback Response";
}
}
