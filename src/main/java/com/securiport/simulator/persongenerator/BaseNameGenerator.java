package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public abstract class BaseNameGenerator implements NameGenerator {
	

	protected NameData NameFile;
	
	public abstract ArrayList<String> getName(RandomSeed R);
		
	public NameData parseJson(String fileName) throws FileNotFoundException {
		// Method to parse the .json file. 
		FileReader data = new FileReader(fileName);
		JsonReader fr = new JsonReader(data);
		Gson gson = new Gson();
		NameData nameData = gson.fromJson(fr, NameData.class);
		return nameData;
	}
	
	public void initialize(String NameFile) {
		try {
			this.NameFile = parseJson(NameFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}