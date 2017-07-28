package com.securiport.simulator.persongenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class NameDataReader extends BaseNameGenerator implements NameGenerator {
	
	@Override
	public String getName(RandomSeed R) {
		 
		
		BufferedReader br = null;
		String[] fileNames = {this.firstNameFile, this.lastNameFile};
		String[] personNames = new String[2];
		for ( int i=0; i < fileNames.length; i++ ) {
			try {
				br = new BufferedReader(new FileReader(fileNames[i]));
				int randRow = R.getNum(13514);
				int counter = 0;
				String line;
				String name = null;
				
				while ( (line = br.readLine()) != null && counter < randRow ) {
					name = line;
					counter++;
				}
				personNames[i] = name;
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String firstName = personNames[0];
		String lastName = personNames[1];
		return firstName + " " + lastName;
	}

	public void initialize(String firstNameFile, String lastNameFile, int seed) {
		// TODO Auto-generated method stub
		
	}
}