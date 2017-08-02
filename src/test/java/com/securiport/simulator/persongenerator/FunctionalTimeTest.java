package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.securiport.simulator.persongenerator.FunctionalTimeGenerator.functionalType;
import com.securiport.simulator.persongenerator.TimingGeneratorFactory.GeneratorType;

public class FunctionalTimeTest {
	
	private TimingGeneratorFactory _tFactory;
	private LocalDateTime _min;
	private LocalDateTime _max;
	private long _numberToGenerate;
	private TimingGenerator _ftg;

	private GeneratorType _typ;
	
	
	@Before
	public void beforeTest() {
		_tFactory = new TimingGeneratorFactory();
		_min = LocalDateTime.now();
		_max = _min.plusHours(3);
		_numberToGenerate = 20;
		_typ = GeneratorType.LOGISTIC;
	}
	
	@Test
	public void testLogisticTimingGenerator() {
		
		LocalDateTime min = LocalDateTime.now();
		LocalDateTime max = min.plusHours(21);
		long generateCount = 20000;
		
		
		_ftg = _tFactory.getGenerator(_typ);
		_ftg.initialize(min, max, generateCount);
	
		for ( int i=0; i<generateCount; i++ ) {
			System.out.println(_ftg.getNextTime());
			//_ftg.getNextTime(functionalType.EXPONENTIAL);
		}
	}
//	@Test 
//	public void calculatorTest() {
//		for ( int i=1; i<=10; i++ ) {
//			double x = i*8000000.0;
//			double y = (1.5*x)/(9*8000000);
//			System.out.println(y);
//			System.out.println((1.1*(9*8000000))/(1 + Math.exp(-4*y)) + 8000000);
//		}
//	}
}