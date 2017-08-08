package com.securiport.simulator.persongenerator.model;

public class Airline {
	public String countryName;
	public String airlineCode;
	public String airlineName;
	
	public String getCountryName() {
		return countryName;
	}
	
	public String getAirlineCode() {
		return airlineCode;
	}
	
	public String getAirlineName() {
		return airlineName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airlineCode == null) ? 0 : airlineCode.hashCode());
		result = prime * result + ((airlineName == null) ? 0 : airlineName.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
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
		Airline other = (Airline) obj;
		if (airlineCode == null) {
			if (other.airlineCode != null)
				return false;
		} else if (!airlineCode.equals(other.airlineCode))
			return false;
		if (airlineName == null) {
			if (other.airlineName != null)
				return false;
		} else if (!airlineName.equals(other.airlineName))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airline [countryName=" + countryName + ", airlineCode=" + airlineCode + ", airlineName=" + airlineName
				+ "]";
	}
	
	
}