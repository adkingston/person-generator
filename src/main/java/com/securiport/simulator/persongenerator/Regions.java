package com.securiport.simulator.persongenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Regions {
	@Expose
	public String Region; 
	@Expose
	public ArrayList<Countries> Countries;
	
	public String getRegionName() {
		return Region;
	}
	
	public ArrayList<Countries> getCountries() {
		return Countries;
	}
	
	public int numCountries() {
		return Countries.size();
	}
}