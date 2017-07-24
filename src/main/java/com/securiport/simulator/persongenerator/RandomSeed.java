package com.securiport.simulator.persongenerator;

import java.util.Random;

public class RandomSeed extends BaseRandomGenerator implements RandomGenerator {
	


	public int getNum(int seed) {
		Random randNum = new Random(seed);
		int numRows = 1000; // 1000 rows in random_name.csv
		int r = randNum.nextInt(numRows); // 13514 used for now - current number of rows in all csv files. 
		return r;	
	}
	
	public int showSeed() {
		return this.seed;
	}
	
	@Override
	public int nextNum() {
		this.seed++;
		return getNum(this.seed);
	}
}