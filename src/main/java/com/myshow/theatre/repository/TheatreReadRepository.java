package com.myshow.theatre.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.myshow.theatre.model.Cinema;
import com.myshow.theatre.model.Movie;
import com.myshow.theatre.model.MovieShow;
import com.myshow.theatre.model.request.MovieShowFilter;

@Repository
public class TheatreReadRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<MovieShow> findByMovieName(String movieName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("MOVIE_NAME", movieName);
		return namedParameterJdbcTemplate.query("SELECT * FROM SHOW_DETAIL where MOVIE = :MOVIE_NAME", paramMap,
				new RowMapper<MovieShow>() {

					@Override
					public MovieShow mapRow(ResultSet rs, int rowNum) throws SQLException {
						Movie movie = new Movie(rs.getString("MOVIE"), rs.getString("MOVIE"), rs.getInt("DURATION"));
						Cinema cinema = new Cinema();
						cinema.setCinemaCity(rs.getString("CITY"));
						cinema.setCinemaName(rs.getString("CINEMA"));

						MovieShow show = MovieShow.builder().cinema(cinema).movie(movie)
								.showtime(rs.getTimestamp("SHOW_TIME")).build();

						return show;
					}
				});
	}

	public List<MovieShow> findByMovieNameAndShowDate(MovieShowFilter filter) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("MOVIE_NAME", filter.getMovie());
		paramMap.put("SHOW_DATE", filter.getShowDate());
		return namedParameterJdbcTemplate.query(
				"SELECT * FROM SHOW_DETAIL where MOVIE = :MOVIE_NAME AND FORMATDATETIME(SHOW_TIME,'dd-MM-yyyy') = :SHOW_DATE",
				paramMap, new RowMapper<MovieShow>() {

					@Override
					public MovieShow mapRow(ResultSet rs, int rowNum) throws SQLException {
						int showDuration = rs.getInt("DURATION");
						Movie movie = new Movie(rs.getString("MOVIE"), rs.getString("MOVIE"), showDuration);
						Cinema cinema = new Cinema();
						cinema.setCinemaCity(rs.getString("CITY"));
						cinema.setCinemaName(rs.getString("CINEMA"));

						MovieShow show = MovieShow.builder().cinema(cinema).movie(movie)
								.showtime(rs.getTimestamp("SHOW_TIME")).showId(rs.getInt("SHOW_ID")).build();
						
						LocalDateTime showStartTime = rs.getTimestamp("SHOW_TIME").toLocalDateTime();
						LocalDateTime showEndTime = showStartTime.plus(Duration.of(showDuration, ChronoUnit.MINUTES));
						show.setShowRunning(showStartTime.isBefore(LocalDateTime.now())
								&& showEndTime.isAfter(LocalDateTime.now()));

						return show;
					}
				});
	}
}
