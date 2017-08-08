package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomGeneratorTest {
	
	private RandomSeed _seed1;
	private RandomSeed _seed2;
	private long RAND_RANGE_MIN = 1235165;
	private long RAND_RANGE_MAX = 1435165;

	
	@Before 
	public void beforeTest() {
		_seed1 = new RandomSeed(5);
		_seed2 = new RandomSeed(5);
	}

	
	@Test
	public void seededResultsRandInt() {
		// test to see if number lists are random, and if same seed results in same list of numbers
		int iterations = 3000;
		int n1;
		int n2;
		for (int i=0; i<iterations; i++) {
			n1 = _seed1.getNum(1000);
			n2 = _seed2.getNum(1000);
			assertTrue(n1 == n2);
		}
	}

	@Test
	public void seededResultsRandDoub() {
		// test to see if number lists are random, and if same seed results in same list of numbers
		int iterations = 3000;
		double n1;
		double n2;
		for (int i=0; i<iterations; i++) {
			n1 = _seed1.nextDouble();
			n2 = _seed2.nextDouble();
			assertTrue(n1 == n2);
		}
	}
	
	@Test
	public void seededResultsRandRange() {
		// test to see if number lists are random, and if same seed results in same list of numbers
		int iterations = 3000;
		long n1;
		long n2;
		for (int i=0; i<iterations; i++) {
			n1 = _seed1.randRangeL(RAND_RANGE_MIN, RAND_RANGE_MAX);
			n2 = _seed2.randRangeL(RAND_RANGE_MIN, RAND_RANGE_MAX);
			assertTrue(n1 == n2);
		}
	}
}