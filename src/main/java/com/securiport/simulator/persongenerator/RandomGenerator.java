package com.securiport.simulator.persongenerator;

public interface RandomGenerator {
	
	/**
	 * Produces random name based on strategy
	 */
	
	int nextNum();
	
	void initialize(int seed);
}