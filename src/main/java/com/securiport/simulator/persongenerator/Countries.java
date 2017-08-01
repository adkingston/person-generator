package com.securiport.simulator.persongenerator;

import java.util.Map;

import com.google.gson.annotations.Expose;

public class Countries {
	@Expose
	public String Country;
	@Expose
	public Map<String, Double> Ages;

	public String getCountryName() {
		return Country;
	}
	
	public Map<String, Double> getAges() {
		return Ages;
	}
	
	public int size() {
		return Ages.size();
	}
}