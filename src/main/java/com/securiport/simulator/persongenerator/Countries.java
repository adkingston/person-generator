package com.securiport.simulator.persongenerator;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Countries {
	@Expose
	public String Country;
	public ArrayList<String> Cities;
	public Map<String, Double> Ages;

	public String getCountryName() {
		return Country;
	}
	public ArrayList<String> getCities() {
		return Cities;
	}
	
	public Map<String, Double> getAges() {
		return Ages;
	}
	
	public int size() {
		return Ages.size();
	}
}