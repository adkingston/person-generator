package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;

public class Region {
	
	public String regionName; 
	public ArrayList<Country> countries;
	
	public String getRegionName() {
		return regionName;
	}
	
	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	public int numCountries() {
		return countries.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countries == null) ? 0 : countries.hashCode());
		result = prime * result + ((regionName == null) ? 0 : regionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		if (countries == null) {
			if (other.countries != null)
				return false;
		} else if (!countries.equals(other.countries))
			return false;
		if (regionName == null) {
			if (other.regionName != null)
				return false;
		} else if (!regionName.equals(other.regionName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Region [regionName=" + regionName + ", countries=" + countries.toString() + "]";
	}
}