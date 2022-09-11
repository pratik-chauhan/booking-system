package com.myshow.theatre.model.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MovieShowDTO {

	private Integer movieId;
	private Integer cinemaId;
	@JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "MM-dd-yyyy HH:mm:ss")
	private Timestamp showtime;
	private Integer showId;

}
