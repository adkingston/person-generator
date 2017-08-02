package com.securiport.simulator.persongenerator;

public interface NameGenerator {
	
	/**
	 * Produces random name based on strategy
	 */
	
	String[] getName(RandomSeed R);
	
	void initialize(String NameFile);
}