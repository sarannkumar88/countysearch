package com.fleet.counties.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleet.counties.model.County;
import com.fleet.counties.repository.CountiesRepository;

@Service
public class CountiesService {
	@Autowired
	CountiesRepository countiesRepository;
	
	public Iterable<County> saveAll(List<County> counties){
		return countiesRepository.saveAll(counties);
	}
	
	public List<County> findAllByName(String name){
		return countiesRepository.findTop5ByNameContainingIgnoreCaseOrderByNameAsc(name);
	}
	
	public List<County> findAllByNameAndState(String name, String state){
		return countiesRepository.findTop5ByNameIgnoreCaseAndStateIgnoreCaseOrderByNameAsc(name, state);
	}
	
	public List<County> findAllByState(String state){
		return countiesRepository.findTop5ByStateIgnoreCaseOrderByNameAsc(state);
	}
}
