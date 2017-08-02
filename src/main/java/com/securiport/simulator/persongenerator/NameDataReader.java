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
//		BufferedReader br = null;
//		String[] personNames = new String[4];
//		int randRow = 0;
//		int counter = 0;
//		String line;
//		String csvSplitBy = ",";
//		String[] name = new String[4];
//
//		try {
//			br = new BufferedReader(new FileReader(this.NameFile));
//			for (int i=0; i<2; i++) {
//
//				randRow = R.getNum(12762);
//	
//				while ( (line = br.readLine()) != null && counter < randRow ) {
//					counter++;
//				}
//				name = line.trim().split(csvSplitBy);
//				
//				if (i==0) {
//					personNames[0] = name[0];
//					personNames[1] = name[1];
//					personNames[3] = name[3];
//				} else {
//					personNames[2] = name[2];
//				}
//			}
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//
//		return personNames;
////		String title = personNames[0];
////		String firstName = personNames[1];
////		String lastName = personNames[2];
////		String gender = personNames[3];
////		return title + " " + firstName + " " + lastName + " (" + gender + ")";
	}

	
}