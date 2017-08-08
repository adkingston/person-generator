package com.securiport.simulator.persongenerator;

import java.util.ArrayList;

public interface NameGenerator {
	
	/**
	 * Produces random name based on strategy
	 */
	
	ArrayList<String> getName(RandomSeed seed);
	
	void initialize(String NameFile);
}