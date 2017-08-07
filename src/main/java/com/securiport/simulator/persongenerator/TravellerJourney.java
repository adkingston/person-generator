package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TravellerJourney {
	
	private static final int MAXIMUM_CONNECTIONS = 10;
	private static final int MINIMUM_CONNECTIONS = 7;

	public boolean isConnecting(RandomSeed R) {
		double r = R.nextDouble();
		return r <= 0.45;
	}
	
	public String flyingFrom(RandomSeed R) throws FileNotFoundException {
		NationGenerator nations = new NationGenerator();
		return nations.getCountry(R).getCountryName();
	}
	
	public Map<String, Integer> onwardTo(RandomSeed R) throws FileNotFoundException {
		/**
		 * Generates uniformly random list of 7-10 countries taken from citiesandAgeDatat.json. 
		 * Will need to move getCountry method to more logical location. 
		 */
		ArrayList<Integer> range = new ArrayList<Integer>(Arrays.asList(MINIMUM_CONNECTIONS, MAXIMUM_CONNECTIONS));
		Map<String, Integer> connectingFlights = new HashMap<String, Integer>();
		NationGenerator nations = new NationGenerator();
		int r = R.randRange(range);
		for (int i=0; i<r; i++) {
			String newFlight = nations.getCountry(R).getCountryName();
			if (!connectingFlights.containsKey(newFlight)) {
				connectingFlights.put(newFlight, 0); 
			} else { 
				i--; // if country chosen is already in the list, try again 
			}
		}
		return connectingFlights;	
	}

	public Map<String, Integer> connectionDeparted(Map<String, Integer> Connections, String key, RandomSeed R) throws FileNotFoundException {
		Connections.remove(key);
		NationGenerator nations = new NationGenerator();
		String newConnection = nations.getCountry(R).getCountryName();
		Connections.put(newConnection, 0);
		return Connections;
	}
	
	public Map<String, Integer> passengerBoarded(Map<String, Integer> Connections, String flight) {
		Connections.put(flight, Connections.get(flight) + 1);
		return Connections;
	}
	
	public String getConnection(Map<String, Integer> Connections, RandomSeed R) throws FileNotFoundException {
		// finds connection based on list built by method above. Probability is uniformly distributed.
		int r = R.getNum(Connections.size()-1);
		ArrayList<String> connectingTo = new ArrayList<String>(Connections.keySet()); 
		return connectingTo.get(r);
	}
}