package com.securiport.simulator.persongenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSeed {
	
	private Random _randNum;


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
	
	public int randRange(ArrayList<Integer> A) {
		// Don't want newborns flying! 
		int a = A.get(0);
		int b = A.get(1);
		if (a == 0) {
			a = 1;
		}
		int R = b - a;
		return _randNum.nextInt(R) + a;
	}
	
	public long randRangeL(long a, long b) {
		return a + (long) _randNum.nextDouble()*(b-a);
	}
}