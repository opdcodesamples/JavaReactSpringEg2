package com.osmand.spring.react.example.one.services;

import java.time.Duration;

import com.osmand.spring.react.example.one.domain.Quote;

import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {
	
	Flux<Quote> fetchQuotesStream(Duration period);

}
