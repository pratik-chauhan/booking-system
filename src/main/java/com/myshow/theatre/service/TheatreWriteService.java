package com.myshow.theatre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshow.theatre.model.request.MovieShowDTO;
import com.myshow.theatre.repository.TheatreWriteRepository;

@Service
public class TheatreWriteService {
	
	@Autowired
	TheatreWriteRepository theatreWriteRepository;
	
	public MovieShowDTO createShow(MovieShowDTO showRequest) {
		MovieShowDTO response = theatreWriteRepository.createShow(showRequest);
		return response;
	}
	
	public MovieShowDTO updateShow(MovieShowDTO showRequest) {
		MovieShowDTO response = theatreWriteRepository.updateShow(showRequest);
		return response;
	}

	public void deleteShow(Integer showId) {
		theatreWriteRepository.deleteShow(showId);		
	}

}
