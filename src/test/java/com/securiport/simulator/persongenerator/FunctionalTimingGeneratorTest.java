package com.securiport.simulator.persongenerator;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import com.securiport.simulator.persongenerator.timegenerator.TimingGenerator;
import com.securiport.simulator.persongenerator.timegenerator.TimingGeneratorFactory;
import com.securiport.simulator.persongenerator.timegenerator.TimingGeneratorFactory.GeneratorType;

public class FunctionalTimingGeneratorTest {
	
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
		_ftg = _tFactory.getGenerator(_typ);
		_ftg.initialize(_min, _max, _numberToGenerate);
		for ( int i=0; i<_numberToGenerate; i++ ) {
			System.out.println(_ftg.getNextTime());
		}
	}
}