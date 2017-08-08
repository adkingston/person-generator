package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class NameGeneratorTest {
	
	private NameDataReader _ndr;
	private RandomSeed _seed;
	private String _nameData;


	/**
	 * Default test parameters: generate 10 random names from collection in csv files.
	 */
//	
	@Before 
	public void beforeTest() {
		_ndr = new NameDataReader();
		_seed = new RandomSeed(5);
		_nameData = "Resources/names.json";
	}
	
	@Test
	public void goodInitializationTest() {
		try {
			_ndr.initialize(_nameData);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test 
	public void badInitializationTest() {
		try {
			_ndr.initialize(null);
			fail("Expected to catch bad file name");
		} catch (Exception ex) {
		}
	}
	
	@Test 
	public void randomNameTest() {
		// check that names don't repeat
		ArrayList<String> testNames = new ArrayList<String>();
		try {
			_ndr.initialize(_nameData);
			for (int i=0; i<50000; i++) {
				ArrayList<String> fullName = _ndr.getName(_seed);
				testNames.add(fullName.toString());
			}
			ArrayList<String> noDups = new ArrayList<String>();
			Set<String> removeDuplicates = new HashSet<>();
			removeDuplicates.addAll(testNames);
			noDups.addAll(removeDuplicates);
			
			if (noDups.size() == testNames.size()) {
				Collections.sort(noDups);
				Collections.sort(testNames);
				assertTrue(noDups.equals(testNames));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}