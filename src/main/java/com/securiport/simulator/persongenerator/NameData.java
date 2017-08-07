package com.securiport.simulator.persongenerator;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.securiport.simulator.persongenerator.model.FirstNames;


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