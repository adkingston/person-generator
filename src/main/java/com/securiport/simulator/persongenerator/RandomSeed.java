package com.securiport.simulator.persongenerator;

import java.util.Random;

public class RandomSeed {
	


	private Random _randNum;
	
	public RandomSeed () {
		Long M = System.currentTimeMillis();
		String F = Long.toString(M);
		int S = Integer.parseInt(F.substring(F.length()-10));
		_randNum = new Random(S);
	}

	public RandomSeed(int S) {
		_randNum = new Random(S);
	}
	
	public int getNum(int maxRows) {
		int r = _randNum.nextInt(maxRows); // 13514 used for now - current number of rows in all csv files. 
		return r;	
	}
}