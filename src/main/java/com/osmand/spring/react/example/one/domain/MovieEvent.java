package com.osmand.spring.react.example.one.domain;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//@AllArgsConstructor
public class MovieEvent {
	
	private String movieId;
	private Date date;
	
	public MovieEvent(String movieId, Date date) {
		this.date = date;
		this.movieId = movieId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
