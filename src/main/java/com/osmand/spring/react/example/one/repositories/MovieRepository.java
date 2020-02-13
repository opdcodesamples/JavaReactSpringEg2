package com.osmand.spring.react.example.one.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.osmand.spring.react.example.one.domain.Movie;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String>{

}
