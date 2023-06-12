package com.fleet.counties.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleet.counties.model.County;

@Repository
public interface CountiesRepository extends JpaRepository<County, String> {
	
	List<County> findTop5ByNameContainingIgnoreCaseOrderByNameAsc(String name);
	
	List<County> findTop5ByNameIgnoreCaseAndStateIgnoreCaseOrderByNameAsc(String name, String state);
	
	List<County> findTop5ByStateIgnoreCaseOrderByNameAsc(String name);
}
