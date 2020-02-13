package com.osmand.spring.react.example.one.services;

import com.osmand.spring.react.example.one.domain.Movie;
import com.osmand.spring.react.example.one.domain.MovieEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
	
	Flux<MovieEvent> events(String movieId);
	
	Mono<Movie> getMovieById(String id);
	
	Flux<Movie> getAllMovies();

}
