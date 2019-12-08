package com.maltem.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltem.demo.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		//read json file and convert to movie object
		Movie movie = objectMapper.readValue(new File("movies.json"), Movie.class);

		//print customer details
		System.out.println(movie);
	}*/
}
