package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Continent {
	@Expose
	public String Continent;
	@Expose
	public ArrayList<Regions> Regions; 
	
	public String getContinentName() {
		return Continent;
	}
	
	public ArrayList<Regions> getRegions() {
		return Regions;
	}
	
	public int numRegions() {
		return Regions.size();
	}
}