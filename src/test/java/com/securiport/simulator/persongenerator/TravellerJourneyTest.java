package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TravellerJourneyTest {
	
	private TravellerJourney _T;
	private RandomSeed _R;
	
	@Before
	public void Before() {
		_T = new TravellerJourney();
		int Seed = 1635465313;
		_R = new RandomSeed(Seed);
	}
	
	@Test
	public void onwardJourneyTest() throws FileNotFoundException {
		ArrayList<String> connections = _T.onwardTo(_R);
 		System.out.println(connections.toString());
	}
	
	@Test
	public void connectionsTest() throws FileNotFoundException {
		ArrayList<String> connections = _T.onwardTo(_R);
		for (int i=0; i<10; i++) {
			String connect = _T.getConnection(connections, _R);
			System.out.println(connect);
		}
	}
}