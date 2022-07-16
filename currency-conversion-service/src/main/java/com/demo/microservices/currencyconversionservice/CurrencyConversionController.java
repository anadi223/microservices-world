package com.demo.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

        @RestController
        public class CurrencyConversionController {

//            @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
//            public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
//                HashMap<String,String> uriVariables = new HashMap<>(); // new hashmap to get values for from and to put in response entity
//                uriVariables.put("from",from);
//                uriVariables.put("to",to);
//        ResponseEntity<CurrencyConversion> responseEntity =  new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables); //* Rest Template is the class that is used to connect two microservice into one and get the response
//        CurrencyConversion currencyConversion = responseEntity.getBody(); // to extract the response body
//        return new CurrencyConversion(currencyConversion.getId(), from, to , quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
//    }

            @Autowired
            private CurrencyExchangeProxy proxy;
            @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
            public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
                CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from,to);
                return new CurrencyConversion(currencyConversion.getId(), from, to , quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
            }

}
