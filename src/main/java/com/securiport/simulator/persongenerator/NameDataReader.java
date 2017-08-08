package com.securiport.simulator.persongenerator;

import java.util.ArrayList;
//import java.util.Arrays;

import com.securiport.simulator.persongenerator.model.FirstName;

public class NameDataReader extends BaseNameGenerator implements NameGenerator {
	

	@Override
	public ArrayList<String> getName(RandomSeed seed) {
		// parses .json file and generates a name with title and gender at random
		ArrayList<String> nameData = new ArrayList<String>();
		
		/*
		 * json structure: { LirstNames : [--list of names--], FirstNames : [ ..., { gender : '', first : '', title : '' }, ... ] }
		 */
		ArrayList<FirstName> fN = this.NameFile.getFirstNameData();
		ArrayList<String> lN = this.NameFile.getLastNames();
		
		int a = seed.getNum(lN.size());
		int b = seed.getNum(fN.size());
		
		String last = lN.get(a);
		
		FirstName forename = fN.get(b);
		
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