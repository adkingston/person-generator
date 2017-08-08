package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;

public class World {

	private ArrayList<Continent> World;
	
	public ArrayList<Continent> getContinents() {
		return World;
	}
	
	public int numContinents() {
		return World.size();
	}

	@Override
	public String toString() {
		return "World [World=" + World + "]";
	}
}