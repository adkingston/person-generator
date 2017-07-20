package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class UniformTimingGeneratorTest {
	
	private UniformTimingGenerator _utg;
	private LocalDateTime _min;
	private LocalDateTime _max;
	private long _numberToGenerate;
	
	/**
	 * Default test parameters - generate 10 things 1 second apart starting now.
	 */
	@Before
	public void beforeTest() {
		_utg = new UniformTimingGenerator();	
		_min = LocalDateTime.now();
		_max = _min.plusSeconds(10);
		_numberToGenerate = 10;
	}
	
	@Test
	public void testGoodInitialize() {	
		/**
		 * Do a correct initialization
		 */
		try {
			_utg.initialize(_min, _max, _numberToGenerate);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
		
	@Test
	public void testBadCountInitialize() {	
		/**
		 * Do a bad count initialization
		 */
		try {
			_utg.initialize(_min, _max, -1);
			fail("Expected to throw an exception when given a negative count");
		} catch (Exception ex) {
		}
	}
	
	@Test
	public void testNullMinMaxInitialize() {	
		/**
		 * Do a bad count initialization
		 */
		try {
			_utg.initialize(null, _max, _numberToGenerate);
			fail("Expected to throw an exception when given a null minimum");

			_utg.initialize(_min, null, _numberToGenerate);
			fail("Expected to throw an exception when given a null maximum");

			_utg.initialize(null, null, _numberToGenerate);
			fail("Expected to throw an exception when given a null minimum and maximum");
		} catch (Exception ex) {
		}
	}

	@Test
	public void testUniformTimingGenerator() {
		
		LocalDateTime min = LocalDateTime.now();
		LocalDateTime max = min.plusSeconds(10);
		long generateCount = 10;
		
		_utg.initialize(min, max, generateCount);
		
		LocalDateTime expected = min;
		LocalDateTime timeStamp = null;
		
		/**
		 * This should give 10 good results separated by 1 second
		 */
		for (int i = 0; i < generateCount; i ++) {
			timeStamp = _utg.getNextTime();
			
//			System.out.println("Call " + (i+1) + ": " + timeStamp);
			assertTrue(timeStamp.equals(expected));
			expected = expected.plusSeconds(1);
		}
		
		/**
		 * This should give a null result
		 */
		timeStamp = _utg.getNextTime();
//		System.out.println("Call 11: " + timeStamp);
		assertNull(timeStamp);
		
	}

}
