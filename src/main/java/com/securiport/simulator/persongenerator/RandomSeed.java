package com.securiport.simulator.persongenerator;

import java.util.Random;

public class RandomSeed {
	
	private Random _randNum;
	
	public RandomSeed() {
		Long M = System.currentTimeMillis();
		String F = Long.toString(M);
		int S = Integer.parseInt(F.substring(F.length()-10));
		System.out.println("Seed is: " + S);
		_randNum = new Random(S);
	}

	public RandomSeed(int S) {
		System.out.println("Seed is: " + S);
		_randNum = new Random(S);
	}
	
	
	public int getNum(int maxRows) {
		return _randNum.nextInt(maxRows);	
	}
}