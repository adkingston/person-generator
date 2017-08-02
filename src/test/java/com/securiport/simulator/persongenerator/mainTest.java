package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class mainTest {
	
	private String[] args = {"08-02-2017 06:00:00", "08-03-2017 03:00:00", "1000", "uniform"};
	
	@Test 
	public void mainTest() throws FileNotFoundException {
		// Want to see if we get an error after 10000 runs
		int counter = 0;
		MainProgram Main = new MainProgram();
		while ( counter < 1000 ) {
			Main.main(args);
			System.out.println(counter);
			counter++;
		}
	}
}