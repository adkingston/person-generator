package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;


public class World {
	@Expose
	private ArrayList<Continent> World;
	
	public ArrayList<Continent> getContinents() {
		return World;
	}
	
	public int numContinents() {
		return World.size();
	}
}