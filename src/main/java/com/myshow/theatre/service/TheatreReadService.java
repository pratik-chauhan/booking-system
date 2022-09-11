package com.myshow.theatre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshow.theatre.model.MovieShow;
import com.myshow.theatre.model.request.MovieShowFilter;
import com.myshow.theatre.repository.TheatreReadRepository;

@Service
public class TheatreReadService {
	
	@Autowired
	TheatreReadRepository theatreReadRepository;
	
	public List<MovieShow> findByMovieName(String movieName){
		return theatreReadRepository.findByMovieName(movieName);
	}
	
	public List<MovieShow> findByMovieNameAndShowDate(MovieShowFilter filter){
		return theatreReadRepository.findByMovieNameAndShowDate(filter);
	}

}
