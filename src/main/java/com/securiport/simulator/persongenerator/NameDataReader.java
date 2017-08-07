package com.securiport.simulator.persongenerator;

//import java.io.BufferedReader;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class NameDataReader extends BaseNameGenerator implements NameGenerator {
	

	@Override
	public ArrayList<String> getName(RandomSeed R) {
		 
		ArrayList<String> nameData = new ArrayList<String>();
		
		ArrayList<FirstNames> fN = this.NameFile.getFirstNameData();
		ArrayList<String> lN = this.NameFile.getLastNames();
		
		int a = R.getNum(lN.size());
		int b = R.getNum(fN.size());
		
		String last = lN.get(a);
		
		FirstNames forename = fN.get(b);
		
		String title = forename.getTitle();
		String first = forename.getFirstName();
		String gender = forename.getGender();
		
		nameData.add(title);
		nameData.add(first);
		nameData.add(last);
		nameData.add(gender);
		
		return nameData;
	}

	
}