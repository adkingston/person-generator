package com.securiport.simulator.persongenerator;

public interface NameGenerator {
	
	/**
	 * Produces random name based on strategy
	 */
	
	String getName();
	
	void initialize(String firstNameFile, String lastNameFile);
}