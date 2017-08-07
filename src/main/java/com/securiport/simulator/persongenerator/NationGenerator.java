package com.securiport.simulator.persongenerator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.securiport.simulator.persongenerator.model.Continent;
import com.securiport.simulator.persongenerator.model.Countries;
import com.securiport.simulator.persongenerator.model.Regions;
import com.securiport.simulator.persongenerator.model.World;

/* @author akingston */

public class NationGenerator {
	
	private static String AGE_DATA = "dataFiles/citiesandAgeData.json";
	
	public World parseJson(String fileName) throws FileNotFoundException {
		// Method to parse the .json file. 
		FileReader data = new FileReader(fileName);
		JsonReader fr = new JsonReader(data);
		Gson gson = new Gson();
		World world = gson.fromJson(fr, World.class);
		return world;
	}
	
	public Continent nextContinent(World world, RandomSeed R) {
		// Pick a random integer between 0 and the total number of entries in the array
		int contID = R.getNum(world.numContinents());
		// Return the continent at that index. 
		Continent continent = world.getContinents().get(contID);
		return continent;
	}
	
	public Regions nextRegion(Continent continent, RandomSeed R) {
		// Same as nextContinent
		int regionID = R.getNum(continent.numRegions());
		Regions region = continent.getRegions().get(regionID);
		return region;
	}
	
	public Countries nextCountry(Regions region, RandomSeed R) {
		// Same as nextContinent
		int countryID = R.getNum(region.numCountries());
		Countries country = region.getCountries().get(countryID);
		return country;
	}
	
	public Countries getCountry(RandomSeed R) throws FileNotFoundException {
		World world = parseJson(AGE_DATA);
		Continent continent = nextContinent(world, R);
		Regions region = nextRegion(continent, R);
		Countries Nationality = nextCountry(region, R);
		return Nationality;
	}
	
}