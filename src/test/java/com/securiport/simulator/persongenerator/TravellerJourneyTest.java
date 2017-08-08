package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;


import org.junit.Before;
import org.junit.Test;

public class TravellerJourneyTest {
	
	private TravellerJourney _itinerary;
	private RandomSeed _seed;
	private int SEED_VALUE = 1635465313;
	
	@Before
	public void Before() {
		_itinerary = new TravellerJourney();
		_seed = new RandomSeed(SEED_VALUE);
	}
	
	@Test
	public void onwardJourneyTest() throws FileNotFoundException {
		Map<String, Integer> connections = _itinerary.onwardTo(_seed);
 		assertNotNull(connections);
	}
	
	@Test
	public void connectionsTest() throws FileNotFoundException {
		Map<String, Integer> connections = _itinerary.onwardTo(_seed);
		for (int i=0; i<10; i++) {
			String connect = _itinerary.getConnection(connections, _seed);
			assertNotNull(connect);
		}
	}
	
	@Test 
	public void departedConnection() throws FileNotFoundException {
		Map<String, Integer> connections = _itinerary.onwardTo(_seed);
		ArrayList<String> key = new ArrayList<String>(connections.keySet());
		String departed = key.get(2); // say
		Map<String, Integer> newConnections = _itinerary.connectionDeparted(connections, departed, _seed);
		assertFalse(connections.equals(newConnections));
	}
}