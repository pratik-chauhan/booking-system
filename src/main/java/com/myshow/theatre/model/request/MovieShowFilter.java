package com.myshow.theatre.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MovieShowFilter {
	
	private String movie;
	@JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private String showDate;

}
