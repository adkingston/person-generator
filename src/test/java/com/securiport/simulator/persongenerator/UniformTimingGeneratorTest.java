package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class UniformTimingGeneratorTest {

	@Test
	public void test() {
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
