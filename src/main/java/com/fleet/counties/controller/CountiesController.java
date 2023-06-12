package com.fleet.counties.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleet.counties.model.County;
import com.fleet.counties.service.CountiesService;

@RestController
public class CountiesController {

	@Autowired
	CountiesService countiesService;

	@GetMapping(value = "/suggest")
	public ResponseEntity<List<County>> getCounties(@RequestParam(value = "q", required = true) String query){
		List<County> countiesList = new ArrayList<>();
		if(query.contains(","))	{
			String[] params = query.split(",");
			String name = params[0].trim();
			String state = params[1].trim();
			countiesList = countiesService.findAllByNameAndState(name, state);
		}
		else if(query.length()==2) {
			countiesList = countiesService.findAllByState(query);
		}
		else {
			countiesList = countiesService.findAllByName(query);
		}
		if(countiesList!=null) {
			return new ResponseEntity<List<County>>(countiesList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<County>>(countiesList,HttpStatus.ACCEPTED);
		}
	}
}
