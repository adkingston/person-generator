package com.securiport.simulator.persongenerator.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Country {

	public String countryName;
	public ArrayList<String> cities;
	public Map<String, Double> ageDistribution;

	public String getCountryName() {
		return countryName;
	}
	public ArrayList<String> getCities() {
		return cities;
	}
	
	public Map<String, Double> getAgeDistribution() {
		return ageDistribution;
	}
	
	public int size() {
		return ageDistribution.size();
	}
	
	public Continent findContinent() throws FileNotFoundException {
		// from the continent we can find the region easily
		FileReader data = new FileReader("Resources/GlobalAgeDistribution.json");
		JsonReader fr = new JsonReader(data);
		Gson gson = new Gson();
		World world = gson.fromJson(fr, World.class);

		// O.K., but there has to be a better way than going through the whole json file again. 
		ArrayList<Continent> continent = world.getContinents();
		for (int i=0; i<continent.size(); i++) {
			ArrayList<Region> region = continent.get(i).getRegions();
			for (int j=0; j<region.size(); j++) {
				ArrayList<Country> countries = region.get(j).getCountries();
				for (int k=0; k<countries.size(); k++) {
					if (this.equals(countries.get(k))) {
						return continent.get(i);
					}
				}
			} 
		}
		return null; 
	}

	public Region findRegion() throws FileNotFoundException {
		// seriously, must be a better way. 
		Continent continent = this.findContinent();
		ArrayList<Region> regions = continent.getRegions();
		for (int i=0; i<regions.size(); i++) {
			ArrayList<Country> countries = regions.get(i).getCountries();
			for (int j=0; j<countries.size(); j++) {
				if (this.equals(countries.get(j))) {
					return regions.get(i);
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageDistribution == null) ? 0 : ageDistribution.hashCode());
		result = prime * result + ((cities == null) ? 0 : cities.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (ageDistribution == null) {
			if (other.ageDistribution != null)
				return false;
		} else if (!ageDistribution.equals(other.ageDistribution))
			return false;
		if (cities == null) {
			if (other.cities != null)
				return false;
		} else if (!cities.equals(other.cities))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Countries [Country=" + countryName + ", Cities=" + cities.toString() + ", Ages=" + ageDistribution.toString() + "]";
	}
}