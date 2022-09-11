package com.myshow.theatre.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
	
	private String movieName;
	private String movieTitle;
	private Integer duration;

}
