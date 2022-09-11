package com.myshow.theatre;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheatreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheatreApplication.class, args);
	}
	
	 @PostConstruct
	    public void init(){
	      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    }

}
