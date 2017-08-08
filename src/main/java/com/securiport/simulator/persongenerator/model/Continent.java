package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;

public class Continent {
	
	public String continentName;
	public ArrayList<Region> regions; 
	
	public String getContinentName() {
		return continentName;
	}
	
	public ArrayList<Region> getRegions() {
		return regions;
	}
	
	public int numRegions() {
		return regions.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continentName == null) ? 0 : continentName.hashCode());
		result = prime * result + ((regions == null) ? 0 : regions.hashCode());
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
		Continent other = (Continent) obj;
		if (continentName == null) {
			if (other.continentName != null)
				return false;
		} else if (!continentName.equals(other.continentName))
			return false;
		if (regions == null) {
			if (other.regions != null)
				return false;
		} else if (!regions.equals(other.regions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Continent [continentName=" + continentName + ", regions=" + regions.toString() + "]";
	}
}