package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomGeneratorTest {
	
	private int _seed;
	private RandomSeed _rs;
	private RandomSeed _r1;
	private RandomSeed _r2;
	
	@Before 
	public void beforeTest() {
		_seed = 0;
		_rs = new RandomSeed();
		_r1 = new RandomSeed();
		_r2 = new RandomSeed();
	}
	
	@Test
	public void initializationTest() {
		try {
			_rs.initialize(_seed);
		} catch ( Exception ex ) {
			fail(ex.getMessage());
		}
	}
	
	@Test 
	public void observeZeroInitializationTest() {
		// given a zero initialization parameter, will a new seed be assigned as necessary?
		_rs.initialize(_seed);
		assertTrue(_rs.showSeed() != 0);
	}
	
	int S = 659810410; // test seed 
	
	@Test
	public void seededResults() {
		// test to see if number lists are random, and if same seed results in same list of numbers
 
		_r1.initialize(S);
		_r2.initialize(S);
		int iterations = 300000;
		int n1;
		int n2;
		for (int i=0; i<iterations; i++) {
			n1 = _r1.nextNum();
			n2 = _r2.nextNum();
			System.out.println(n1);
			assertTrue(n1 == n2);
		}
	}
}