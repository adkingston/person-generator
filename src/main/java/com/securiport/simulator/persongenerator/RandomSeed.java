package com.securiport.simulator.persongenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSeed {
	
	private Random RAND_NUM;
	
	public RandomSeed(int seed) {
		System.out.println("Seed is: " + seed);
		RAND_NUM = new Random(seed);
	}
	
	public int getNum(int maxRows) {
		return RAND_NUM.nextInt(maxRows);	
	}
	
	public double nextDouble() {
		return RAND_NUM.nextDouble();
	}
	
	public int randRange(ArrayList<Integer> A) {
		int a = A.get(0);
		int b = A.get(1);
		int R = b - a;
		return RAND_NUM.nextInt(R) + a;
	}
	
	public long randRangeL(long a, long b) {
		return a + (long) RAND_NUM.nextDouble()*(b-a);
	}
}