package com.securiport.simulator.persongenerator;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;


public class NameData {

	private ArrayList<String> LastNames;
	private ArrayList<FirstNames> FirstNames;
	
	public ArrayList<FirstNames> getFirstNameData(){
		return FirstNames;
	}
	
	public ArrayList<String> getLastNames() {
		return LastNames;
	}
}