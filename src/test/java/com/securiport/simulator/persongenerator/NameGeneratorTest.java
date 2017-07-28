package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NameGeneratorTest {
	
	private NameDataReader _ndr1;
	private NameDataReader _ndr2;
	private String _fNF;
	private String _lNF;

	/**
	 * Default test parameters: generate 10 random names from collection in csv files.
	 */
	
	@Before
	public void beforeTest() {
		_ndr1 = new NameDataReader();
		_ndr2 = new NameDataReader();

		_fNF = "/Users/Alexander/csv/first_names.csv"; // will have to find better place for these
		_lNF = "/Users/Alexander/csv/last_names.csv";
	}
	
	@Test
	public void goodInitializationTest() {
		try {
			_ndr1.initialize(_fNF, _lNF);
		} catch ( Exception ex ) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void badInitializeTest() {
		try {
			_ndr1.initialize(_fNF, null);
			fail("Expected to catch bad file name");
			
			_ndr1.initialize(null, _lNF);
			fail("Expected to catch bad file name");
			
			_ndr1.initialize(null, null);
			fail("Expected to catch bad file name");
		} catch ( Exception ex ) {
		}
	}
	
	@Test
	public void testNameGenerator() {
		String firstNamePath = "/Users/Alexander/csv/first_names.csv";
		String lastNamePath = "/Users/Alexander/csv/last_names.csv";

		
		_ndr1.initialize(firstNamePath, lastNamePath);
		
		String name1 = null;

		for ( int i = 0; i < 10000; i++ ) {
			
			name1 = _ndr1.getName();
			System.out.println(name1);

		}
	}
	
//	@Test
//	public void testForeignNameGenerator() {
//		// Same test but with names containing non-Latin characters
//		String firstNamePath = "/Users/Alexander/csv/random_names.csv";
//		String lastNamePath = "/Users/Alexander/csv/last_names.csv";
//		
//		_ndr1.initialize(firstNamePath, lastNamePath);
//		
//		String name = null;
//		int count = 0;
//		for ( int i = 0; i < 1000; i++ ) {
//			name = _ndr1.getName();
//			
//			System.out.println(name);
//			count++;
//			//Should print out 1000 names to the console. 			
//		}
//		assertTrue( count == 1000 );
//	}
}