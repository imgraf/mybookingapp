package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyBookingAppApplication {

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MyBookingAppApplication.class, args);

		MovieService movieService = context.getBean(MovieService.class);
		MyBookingAppApplication myApp = context.getBean(MyBookingAppApplication.class);

		Runner runner = new Runner(movieService, myApp);
		runner.run();
	}
}

