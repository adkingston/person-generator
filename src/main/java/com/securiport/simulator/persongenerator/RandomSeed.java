package com.securiport.simulator.persongenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSeed {
	
	private Random _randNum;
	
	public RandomSeed() {
		Long M = System.currentTimeMillis();
		String F = Long.toString(M);
		int S = Integer.parseInt(F.substring(F.length()-10));
		System.out.println("Seed is: " + S);
		_randNum = new Random(S);
	}

	public RandomSeed(int seed) {
		System.out.println("Seed is: " + seed);
		_randNum = new Random(seed);
	}
	
	
	public int getNum(int maxRows) {
		return _randNum.nextInt(maxRows);	
	}
	
	public double nextDouble() {
		return _randNum.nextDouble();
	}
	
	public int randRange(int[] A) {
		// Don't want newborns flying! 
		if (A[0] == 0) {
			A[0] = 1;
		}
		int R = A[1] - A[0];
		return _randNum.nextInt(R) + A[0];
	}
	
	public long randRangeL(long a, long b) {
		return a + (long) _randNum.nextDouble()*(b-a);
	}
}