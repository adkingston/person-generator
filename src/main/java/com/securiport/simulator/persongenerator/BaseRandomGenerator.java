package com.securiport.simulator.persongenerator;

public abstract class BaseRandomGenerator implements RandomGenerator {
	
	protected int seed; 
	
	public abstract int nextNum();
	
	private int getNewSeed() {
		Long M = System.currentTimeMillis();
		String F = Long.toString(M);
		int S = Integer.parseInt(F.substring(F.length()-10));
		return S;
	}
	
	public void initialize(int seed) {
		if (seed == 0) {
			this.seed = getNewSeed();
		} else {
			this.seed = seed;
		}
	}
}