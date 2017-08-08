package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.securiport.simulator.persongenerator.model.Airline;


public class FlightGenerator {
	
	private String AIRLINE_CODES = "Resources/AirlineData.json";
	
	public Airline getAirline(RandomSeed seed) throws FileNotFoundException {
		// parses AIRLINE_CODES and returns random Airline {Country, code} object from json
		
		FileReader data = new FileReader(AIRLINE_CODES);
		JsonReader fr = new JsonReader(data);
		// Need to define type due to format of json file. 
		Type arrayType = new TypeToken<ArrayList<Airline>>(){}.getType();
		
		Gson gson = new Gson();
		ArrayList<Airline> airlines = gson.fromJson(fr, arrayType);
		
		int r = seed.getNum(airlines.size()-1);
		
		return airlines.get(r);
	}

	public ArrayList<String> getFlightInfo(RandomSeed seed) throws FileNotFoundException {
		// Takes Airline object from above and puts it into an ArrayList. 
		Airline airline = getAirline(seed);
		String code = airline.getAirlineCode();
		for (int i=0; i<3; i++) {
			code += seed.getNum(10);
		}
		ArrayList<String> flightInfo = new ArrayList<String>();
		flightInfo.add(code);
		flightInfo.add(airline.getCountryName());
		flightInfo.add(airline.getAirlineName());
		return flightInfo;
	}
}