package com.myshow.theatre.model;


import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieShow {
	private Integer showId;
	private Movie movie;
	private Cinema cinema;
	private Timestamp showtime;
	private Boolean showRunning;

}
