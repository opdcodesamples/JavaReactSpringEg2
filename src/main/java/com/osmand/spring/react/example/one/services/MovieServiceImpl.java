package com.osmand.spring.react.example.one.services;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.osmand.spring.react.example.one.domain.Movie;
import com.osmand.spring.react.example.one.domain.MovieEvent;
import com.osmand.spring.react.example.one.repositories.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository; 
	
	public MovieServiceImpl (MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Flux<MovieEvent> events(String movieId) {
		// returning a new Movie Event
		return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
			movieEventSynchronousSink.next(new MovieEvent(movieId, new Date()));
		}).delayElements(Duration.ofSeconds(2));
	}

	@Override
	public Mono<Movie> getMovieById(String id) {		
		return this.movieRepository.findById(id);
	}

	@Override
	public Flux<Movie> getAllMovies() {
		return this.movieRepository.findAll();
	}

}
