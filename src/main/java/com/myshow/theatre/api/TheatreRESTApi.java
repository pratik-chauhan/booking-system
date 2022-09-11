package com.myshow.theatre.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshow.theatre.model.MovieShow;
import com.myshow.theatre.model.request.MovieShowDTO;
import com.myshow.theatre.model.request.MovieShowFilter;
import com.myshow.theatre.service.TheatreReadService;
import com.myshow.theatre.service.TheatreWriteService;

@RestController
@RequestMapping("theatre/api")
public class TheatreRESTApi {
	
	@Autowired
	private TheatreReadService theatreReadService;
	
	@Autowired
	private TheatreWriteService theatreWriteService;
	
	
	@GetMapping("/")
	public String health() {
		return "Service Up";
	}
	
	//@GetMapping("/movieshow/{movie}")
	public List<MovieShow> movieShowbyMovie(@PathVariable String movie){
		return theatreReadService.findByMovieName(movie);
	}
	
	@PostMapping("/movieshow")
	public List<MovieShow> movieShowbyChoosenDateAndMovie(@RequestBody MovieShowFilter showCriteria){
		return theatreReadService.findByMovieNameAndShowDate(showCriteria);
	}
	
	@PostMapping("/movieshow/create")
	public ResponseEntity<MovieShowDTO> createMovieShow(@RequestBody MovieShowDTO showRequest){
		
		MovieShowDTO response = theatreWriteService.createShow(showRequest);
		return new ResponseEntity<MovieShowDTO>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/movieshow/update/{showId}")
	public ResponseEntity<MovieShowDTO> updateMovieShow(@PathVariable Integer showId,@RequestBody MovieShowDTO showRequest){
		showRequest.setShowId(showId);
		MovieShowDTO response = theatreWriteService.updateShow(showRequest);
		return new ResponseEntity<MovieShowDTO>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/movieshow/delete/{showId}")
	public ResponseEntity<Void> deleteMovieShow(@PathVariable Integer showId){
		theatreWriteService.deleteShow(showId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
