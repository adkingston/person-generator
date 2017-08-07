package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


public class Flight {
	
	private String AIRLINE_CODES = "C:/Users/Alexander/csv/airlineCodes.json";
	
	public Airlines getAirline(RandomSeed R) throws FileNotFoundException {
		// parses AIRLINE_CODES and returns random Airline {Country, code} object from json
		
		FileReader data = new FileReader(AIRLINE_CODES);
		JsonReader fr = new JsonReader(data);
		// Need to define type due to format of json file. 
		Type arrayType = new TypeToken<ArrayList<Airlines>>() {}.getType();
		
		Gson gson = new Gson();
		ArrayList<Airlines> airlines = gson.fromJson(fr, arrayType);
		
		int r = R.getNum(airlines.size()-1);
		
		return airlines.get(r);
	}

	public ArrayList<String> getFlightInfo(RandomSeed R) throws FileNotFoundException {
		// Takes Airline object from above and puts it into an ArrayList. 
		Airlines airline = getAirline(R);
		String code = airline.getCode();
		for (int i=0; i<3; i++) {
			code += R.getNum(10);
		}
		ArrayList<String> flightInfo = new ArrayList<String>();
		flightInfo.add(code);
		flightInfo.add(airline.getCountry());
		return flightInfo;
	}
}