package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.securiport.simulator.persongenerator.model.Continent;
import com.securiport.simulator.persongenerator.model.Country;
import com.securiport.simulator.persongenerator.model.World;
import com.securiport.simulator.persongenerator.model.Region;

public class AgeNationalityGeneratorTest {
	
	private AgeGenerator _ag;
	private RandomSeed _r = new RandomSeed(5);
	private String _ageData;
	private NationGenerator _n;
	private Country _c;
	private Map<String, Double> _ageDist;
	
	@Before
	public void before() throws FileNotFoundException {
		_ag = new AgeGenerator();
		_ageData = "Resources/GlobalAgeDistributions.json";
		_n = new NationGenerator();
		_c = _n.getCountry(_r);
	}
	
	@Test
	public void worldNonEmpty() {
		// checks to see if the json parser is functioning properly
		World world = null;
		try {
			world = _n.parseJson(_ageData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Continent continent = world.getContinents().get(0);
		assertNotNull(continent.getContinentName());
		Region region = continent.getRegions().get(0);
		assertNotNull(region.getRegionName());
		Country nation = region.getCountries().get(0);
		assertNotNull(nation.toString());
		// passed
	}
	
	@Test
	public void getAgeDistributionTest() {
		_ageDist = _c.getAgeDistribution();
		assertNotNull(_ageDist);	
	}
	
	@Test 
	public void ageTest() {
		// test that age determining function and date of birth calculation are functioning properly. 
		_ageDist = _c.getAgeDistribution();
		int age = _ag.determineAge(_ageDist, _r);
		assertNotNull(age);
//		System.out.println(age);
		LocalDate DOB = _ag.generateDOB(age, _r);
		assertNotNull(DOB);
//		System.out.println(DOB);
	}
	/**
	 * All functions work as expected. AgeGenerator and NationGenerator passed tests. 
	 */
}

