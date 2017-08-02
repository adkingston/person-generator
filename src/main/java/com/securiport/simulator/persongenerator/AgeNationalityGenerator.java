package com.securiport.simulator.persongenerator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/* @author akingston */

public class AgeNationalityGenerator extends Passport{
	
	
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
	
	public ArrayList<Integer> getAgeRange(String ages) {
		// Turns string depicting range of ages into a list of two integers 
		ArrayList<Integer> range = new ArrayList<Integer>();
		if (ages.length() == 5) {
			range.add(Integer.parseInt(ages.substring(0, 2)));
			range.add(Integer.parseInt(ages.substring(ages.length()-2, ages.length())));
		} else if (ages.length() == 3 && !ages.substring(2).equals("+")) {
			range.add(Integer.parseInt(ages.substring(0, 1)));
			range.add(Integer.parseInt(ages.substring(2, 3)));
		} else {
			range.add(Integer.parseInt(ages.substring(0, 1)));
			range.add(100);
		}
		return range;
	}
	
	public int nextAge(Map<String, Double> probsMap, RandomSeed R) {
		// get random value between 0 and 1
		double randDouble = R.nextDouble();
		// sort map so that age distribution is in ascending order
		Map<String, Double> sortedAges = (Map<String, Double>) probsMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, 
						Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		// use the random double to determine appropriate key based on summed probabilities:
			/* All probabilities sum to 1. In the map, the next 'probability' value is given by the probability
			 of that age range plus the probabilities of those before it (hence, 80+ is always paired to 1).
			 The age range is determined by the first value in the map which is larger than the random double
			  previously defined */
		String ageRange = null;
		for (String o : sortedAges.keySet()) {
			if (randDouble <= sortedAges.get(o)) {
				ageRange = o;
				break;
			}
		}
		// Convert string representation of age range to integers
		ArrayList<Integer> ageInterval = getAgeRange(ageRange);

		// Get a random integer within the age range
		int Age = R.randRange(ageInterval);
		return Age;
	}
	
	public LocalDate getDOB(int age, RandomSeed R) {
		int month = R.getNum(11);
		
		int currYear = 2017;
		int dummyDay = 1;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(currYear, month, dummyDay);
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = R.getNum(maxDays);
		
		if (day == 0) {
			day ++;
		} 
		month++; // Calendar uses month index 0-11, LocalDate uses 1-12. Add one to convert to LocalDate range.
		
		LocalDate nextBirthday = LocalDate.of(currYear, month, day);
		LocalDate today = LocalDate.now();
		
		int birthYear = 0; // initiate variable 
		
		if (nextBirthday.isAfter(today)) {
			birthYear = today.getYear() - (age + 1); 
		} else {
			birthYear = today.getYear() - age;
		}
		
		LocalDate dateOfBirth = LocalDate.of(birthYear, month, day);
		return dateOfBirth;
	}

}