package com.securiport.simulator.persongenerator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.securiport.simulator.persongenerator.model.Continent;
import com.securiport.simulator.persongenerator.model.Country;
import com.securiport.simulator.persongenerator.model.Region;
import com.securiport.simulator.persongenerator.model.World;

/* @author akingston */

public class NationGenerator {
	
	private static String AGE_DATA = "Resources/GlobalAgeDistributions.json";
	
	public World parseJson(String fileName) throws FileNotFoundException {
		// Method to parse the .json file. 
		FileReader data = new FileReader(fileName);
		JsonReader fr = new JsonReader(data);
		Gson gson = new Gson();
		World world = gson.fromJson(fr, World.class);
		return world;
	}
	
	public Continent nextContinent(World world, RandomSeed seed) {
		// Pick a random integer between 0 and the total number of entries in the array
		int contID = seed.getNum(world.numContinents());
		// Return the continent at that index. 
		Continent continent = world.getContinents().get(contID);
		return continent;
	}
	
	public Region nextRegion(Continent continent, RandomSeed seed) {
		// Same as nextContinent
		int regionID = seed.getNum(continent.numRegions());
		Region region = continent.getRegions().get(regionID);
		return region;
	}
	
	public Country nextCountry(Region region, RandomSeed seed) {
		// Same as nextContinent
		int countryID = seed.getNum(region.numCountries());
		Country country = region.getCountries().get(countryID);
		return country;
	}
	
	public Country getCountry(RandomSeed seed) throws FileNotFoundException {
		World world = parseJson(AGE_DATA);
		Continent continent = nextContinent(world, seed);
		Region region = nextRegion(continent, seed);
		Country Nationality = nextCountry(region, seed);
		return Nationality;
	}
	
}