package com.myshow.theatre.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.myshow.theatre.model.request.MovieShowDTO;

@Repository
public class TheatreWriteRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public MovieShowDTO createShow(MovieShowDTO showRequest) {

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("CINEMA_ID", showRequest.getCinemaId())
				.addValue("MOVIE_ID", showRequest.getMovieId()).addValue("SHOW_TIME", showRequest.getShowtime());

		namedParameterJdbcTemplate.update(
				"INSERT INTO MOVIE_SHOW (MOVIE_ID,CINEMA_ID,SHOW_DATE_TIME) VALUES (:MOVIE_ID,:CINEMA_ID,:SHOW_TIME)",
				parameters, holder);
		showRequest.setShowId(holder.getKey().intValue());

		return showRequest;

	}

	public MovieShowDTO updateShow(MovieShowDTO showRequest) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("SHOW_ID", showRequest.getShowId())
				.addValue("CINEMA_ID", showRequest.getCinemaId()).addValue("MOVIE_ID", showRequest.getMovieId())
				.addValue("SHOW_TIME", showRequest.getShowtime());

		namedParameterJdbcTemplate.update(
				"UPDATE MOVIE_SHOW SET CINEMA_ID = :CINEMA_ID, MOVIE_ID = :MOVIE_ID, SHOW_DATE_TIME = :SHOW_TIME WHERE ID = :SHOW_ID ",
				parameters);

		return showRequest;

	}

	public void deleteShow(Integer showId) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("SHOW_ID", showId);
		int deletedRow = namedParameterJdbcTemplate.update("DELETE MOVIE_SHOW WHERE ID = :SHOW_ID ", parameters);
		System.out.println("Deleted Row count for showID: "+showId+" is "+deletedRow+" ");
	}

}
