package com.securiport.simulator.persongenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class NameDataReader extends BaseNameGenerator implements NameGenerator {
	
	@Override
	public String[] getName(RandomSeed R) {
		 
		
		BufferedReader br = null;
		String[] personNames = new String[4];
		int randRow = 0;
		int counter = 0;
		String line;
		String csvSplitBy = ",";
		String[] name = new String[4];

		try {
			br = new BufferedReader(new FileReader(this.NameFile));
			for (int i=0; i<2; i++) {

				randRow = R.getNum(12762);
	
				while ( (line = br.readLine()) != null && counter < randRow ) {
					counter++;
				}
				name = line.trim().split(csvSplitBy);
				
				if (i==0) {
					personNames[0] = name[0];
					personNames[1] = name[1];
					personNames[3] = name[3];
				} else {
					personNames[2] = name[2];
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return personNames;
//		String title = personNames[0];
//		String firstName = personNames[1];
//		String lastName = personNames[2];
//		String gender = personNames[3];
//		return title + " " + firstName + " " + lastName + " (" + gender + ")";
	}
	
}