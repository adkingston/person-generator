package com.securiport.simulator.persongenerator.model;

public class FirstName{
	
	private String Gender;
	private String First;
	private String Title;
	
	public String getGender() {
		return Gender;
	}
	
	public String getFirstName() {
		return First;
	}
	
	public String getTitle() {
		return Title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((First == null) ? 0 : First.hashCode());
		result = prime * result + ((Gender == null) ? 0 : Gender.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
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
		FirstName other = (FirstName) obj;
		if (First == null) {
			if (other.First != null)
				return false;
		} else if (!First.equals(other.First))
			return false;
		if (Gender == null) {
			if (other.Gender != null)
				return false;
		} else if (!Gender.equals(other.Gender))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FirstNames [Gender=" + Gender + ", First=" + First + ", Title=" + Title + "]";
	}
	
}