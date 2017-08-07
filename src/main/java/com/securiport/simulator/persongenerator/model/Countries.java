package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Countries {


	@Expose
	public String Country;
	public ArrayList<String> Cities;
	public Map<String, Double> Ages;

	public String getCountryName() {
		return Country;
	}
	public ArrayList<String> getCities() {
		return Cities;
	}
	
	public Map<String, Double> getAges() {
		return Ages;
	}
	
	public int size() {
		return Ages.size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Countries [Country=" + Country + ", Cities=" + Cities + ", Ages=" + Ages + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Ages == null) ? 0 : Ages.hashCode());
		result = prime * result + ((Cities == null) ? 0 : Cities.hashCode());
		result = prime * result + ((Country == null) ? 0 : Country.hashCode());
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
		Countries other = (Countries) obj;
		if (Ages == null) {
			if (other.Ages != null)
				return false;
		} else if (!Ages.equals(other.Ages))
			return false;
		if (Cities == null) {
			if (other.Cities != null)
				return false;
		} else if (!Cities.equals(other.Cities))
			return false;
		if (Country == null) {
			if (other.Country != null)
				return false;
		} else if (!Country.equals(other.Country))
			return false;
		return true;
	}
}