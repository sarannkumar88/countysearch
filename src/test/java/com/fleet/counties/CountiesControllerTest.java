package com.fleet.counties;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fleet.counties.controller.CountiesController;
import com.fleet.counties.model.County;
import com.fleet.counties.service.CountiesService;

@WebMvcTest(CountiesController.class)
public class CountiesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountiesService countiesService;
	private County cowlitzCounty;
	@BeforeEach
	void init() {
		cowlitzCounty = new County();
		cowlitzCounty.setId(1L);
		cowlitzCounty.setName("Cowlitz");
		cowlitzCounty.setState("WA");
		cowlitzCounty.setFips("53015");
	}

	@Test
	void shouldFetchAllMatchedCounties() throws Exception {
		
		List<County> list = new ArrayList<>();
		County county1 =new County();
		county1.setId(1L);
		county1.setName("Adams");
		county1.setState("WA");
		county1.setFips("53001");
		County county2 = new County();
		county2.setId(1L);
		county2.setName("Asotin");
		county2.setState("WA");
		county2.setFips("53003");
		County county3 = new County();
		county3.setId(1L);
		county3.setName("Benton");
		county3.setState("WA");
		county3.setFips("53005");
		County county4 = new County();
		county4.setId(1L);
		county4.setName("Chelan");
		county4.setState("WA");
		county4.setFips("53007");
		County county5 = new County();
		county5.setId(1L);
		county5.setName("Clallam");
		county5.setState("WA");
		county5.setFips("53009");
		list.add(county1);
		list.add(county2);
		list.add(county3);
		list.add(county4);
		list.add(county5);
		
		when(countiesService.findAllByState("wa")).thenReturn(list);
		
		this.mockMvc.perform(get("/suggest").param("q", "wa"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.size()", is(list.size())));
	}
	
	@Test
	void shouldFetchOneCountyByNameAndState() throws Exception {
		List<County> list = new ArrayList<>();
		list.add(cowlitzCounty);
		when(countiesService.findAllByNameAndState("cowlitz", "wa")).thenReturn(list);
		
		this.mockMvc.perform(get("/suggest").param("q", "cowlitz, wa"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is(cowlitzCounty.getName())))
			.andExpect(jsonPath("$[0].state", is(cowlitzCounty.getState())));
	}
	
	@Test
	void shouldFetchAllCountyByName() throws Exception {
		List<County> list = new ArrayList<>();
		County county1 =new County();
		county1.setId(1L);
		county1.setName("Cowley");
		county1.setState("KS");
		county1.setFips("20035");
		County county2 = new County();
		county2.setId(1L);
		county2.setName("Cowlitz");
		county2.setState("WA");
		county2.setFips("53015");
		list.add(county1);
		list.add(county2);
		when(countiesService.findAllByName("cowl")).thenReturn(list);
		
		this.mockMvc.perform(get("/suggest").param("q", "cowl"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is(list.get(0).getName())))
			.andExpect(jsonPath("$[0].fips", is(list.get(0).getFips())));
	}
	
}