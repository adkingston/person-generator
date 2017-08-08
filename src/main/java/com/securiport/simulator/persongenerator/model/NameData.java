package com.securiport.simulator.persongenerator.model;

import java.util.ArrayList;

public class NameData {

	private ArrayList<String> LastNames;
	private ArrayList<FirstName> FirstNames;
	
	public ArrayList<FirstName> getFirstNameData(){
		return FirstNames;
	}
	
	public ArrayList<String> getLastNames() {
		return LastNames;
	}

	@Override
	public String toString() {
		return "NameData [LastNames=" + LastNames + ", FirstNames=" + FirstNames + "]";
	}
}