package com.osmand.spring.react.example.one.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Document
@Data
@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class Movie {
	
	private String id;
	
	@NonNull
	private String title;
	
	public Movie(String title) {
		this.title = title;
	}
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String toString() {
		return "Movie details: Id: " + id + ", title: " + title;
	}
	
}
