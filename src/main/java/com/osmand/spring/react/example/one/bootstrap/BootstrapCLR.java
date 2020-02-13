package com.osmand.spring.react.example.one.bootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.osmand.spring.react.example.one.domain.Movie;
import com.osmand.spring.react.example.one.repositories.MovieRepository;

import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {
	
	private final MovieRepository movieRepository;
	
	public BootstrapCLR(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void run(String... args) throws Exception {
				
		// create movie list
		
		List<String> movies = new ArrayList<>();
		movies.add("Lincoln Lawyer");
		movies.add("Independence Day");
		movies.add("Terminator");
		movies.add("Apollo 13");
		movies.add("Back to Future");
		movies.add("Wizard of Oz");		
		movies.add("Despicables");
		movies.add("Lord of the Rings");
		movies.add("Jack Ryan");
		movies.add("Batman");
		movies.add("Day After Tomorrow");
		
		//movieRepository.deleteAll().block();
		
		
		movieRepository.deleteAll()
						.thenMany(
								Flux
								.just(movies.toArray(new String[movies.size()]))
								.map(title -> new Movie(title))
								.flatMap(movieRepository::save)
								
								).subscribe(null, 
											null, 
											() -> { 
												movieRepository
														.findAll()
														.subscribe(System.out::println);
											});

	}

}
