package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class UniformTimingGeneratorTest {

	@Test
	public void testInitializeUniformTimingGenerator() {	
		UniformTimingGenerator utg = new UniformTimingGenerator();

		LocalDateTime min = LocalDateTime.now();
		LocalDateTime max = min.plusSeconds(10);
		long generateCount = 10;
		
		/**
		 * Do a correct initialization
		 */
		try {
			utg.initialize(min, max, generateCount);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		/**
		 * Do a bad count initialization
		 */
		try {
			utg.initialize(min, max, -1);
			fail("Expected to throw an exception when given a negative count");
		} catch (Exception ex) {
		}
		
	}
	
	@Test
	public void testUniformTimingGenerator() {
		UniformTimingGenerator utg = new UniformTimingGenerator();
		
		LocalDateTime min = LocalDateTime.now();
		LocalDateTime max = min.plusSeconds(10);
		long generateCount = 10;
		
		utg.initialize(min, max, generateCount);
		
		LocalDateTime expected = min;
		LocalDateTime timeStamp = null;
		
		/**
		 * This should give 10 good results separated by 1 second
		 */
		for (int i = 0; i < generateCount; i ++) {
			timeStamp = utg.getNextTime();
			System.out.println("Call " + (i+1) + ": " + timeStamp);
			assertTrue(timeStamp.equals(expected));
			expected = expected.plusSeconds(1);
		}
		
		/**
		 * This should give a null result
		 */
		timeStamp = utg.getNextTime();
		System.out.println("Call 11: " + timeStamp);
		assertNull(timeStamp);
		
	}

}
