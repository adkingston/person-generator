package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomGeneratorTest {
	
	private int _seed;
	private RandomSeed _rs;
	private RandomSeed _r1;
	private RandomSeed _r2;
	private RandomSeed R;

	
//	@Before 
//	public void beforeTest() {
//		_seed = 0;
//		_rs = new RandomSeed();
//		_r1 = new RandomSeed(1);
//		_r2 = new RandomSeed(1);
//	}
//	
//	@Test
//	public void initializationTest() {
//		try {
//			_rs.initialize(_seed);
//		} catch ( Exception ex ) {
//			fail(ex.getMessage());
//		}
//	}
	
	int S = 659810410; // test seed 
	
//	@Test
//	public void seededResults() {
//		// test to see if number lists are random, and if same seed results in same list of numbers
//
//		int iterations = 3;
//		int n1;
//		int n2;
//		for (int i=0; i<iterations; i++) {
//			n1 = _r1.getNum(1000);
//			n2 = _r2.getNum(1000);
//			System.out.println(n1);
//			assertTrue(n1 == n2);
//		}
//	}

	
}