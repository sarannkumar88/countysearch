package com.fleet.counties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.counties.model.County;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fleet.counties.service.CountiesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
public class CountiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountiesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CountiesService countyService) {
		return args -> {
			// read json and write to db

			ObjectMapper mapper = new ObjectMapper(); 
			//TypeReference<List<County>>  typeReference = new TypeReference<List<County>>(){}; 
			InputStream inputStream	= TypeReference.class.getResourceAsStream("/static/data.json"); 
			try {
				//mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				List<County> counties = Arrays.asList((mapper.readValue(inputStream,County[].class)));
				countyService.saveAll(counties); System.out.println("Counties Saved!");
			}
			catch (IOException e){ 
				System.out.println("Unable to save Counties: " +
					e.getMessage()); 
			}


		};
	}


}
