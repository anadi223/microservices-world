package com.demo.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

// To talk to db
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to); //spring data jpa will convert this into a query and get the result
}
