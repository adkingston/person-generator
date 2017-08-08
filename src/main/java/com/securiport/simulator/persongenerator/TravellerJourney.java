package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TravellerJourney {
	
	private static final int MAXIMUM_CONNECTIONS = 10;
	private static final int MINIMUM_CONNECTIONS = 7;

	public boolean isConnecting(RandomSeed seed) {
		double r = seed.nextDouble();
		return r <= 0.45;
	}
	
	public String flyingFrom(RandomSeed seed) throws FileNotFoundException {
		NationGenerator nations = new NationGenerator();
		return nations.getCountry(seed).getCountryName();
	}
	
	public Map<String, Integer> onwardTo(RandomSeed seed) throws FileNotFoundException {
		/**
		 * Generates uniformly random list of 7-10 countries taken from citiesandAgeDatat.json. 
		 * Will need to move getCountry method to more logical location. 
		 */
		ArrayList<Integer> range = new ArrayList<Integer>(Arrays.asList(MINIMUM_CONNECTIONS, MAXIMUM_CONNECTIONS));
		Map<String, Integer> connectingFlights = new HashMap<String, Integer>();
		NationGenerator nations = new NationGenerator();
		int r = seed.randRange(range);
		while (connectingFlights.size() != r) { 
			String newFlight = nations.getCountry(seed).getCountryName();
			if (!connectingFlights.containsKey(newFlight)) {
				connectingFlights.put(newFlight, 0);
			} 
		}
		return connectingFlights;	
	}

	public Map<String, Integer> connectionDeparted(Map<String, Integer> connections, 
												   String departedFlight, RandomSeed seed) throws FileNotFoundException {
		Map<String, Integer> newConnections = new HashMap<String, Integer>(connections);
		newConnections.remove(departedFlight);
		NationGenerator nations = new NationGenerator();
		String newConnection = nations.getCountry(seed).getCountryName();
		newConnections.put(newConnection, 0);
		return newConnections;
	}
	
	public Map<String, Integer> passengerBoarded(Map<String, Integer> Connections, String flight) {
		Connections.put(flight, Connections.get(flight) + 1);
		return Connections;
	}
	
	public String getConnection(Map<String, Integer> Connections, RandomSeed seed) throws FileNotFoundException {
		// finds connection based on list built by method above. Probability is uniformly distributed.
		int r = seed.getNum(Connections.size()-1);
		ArrayList<String> connectingTo = new ArrayList<String>(Connections.keySet()); 
		return connectingTo.get(r);
	}
}