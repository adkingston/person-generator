package com.securiport.simulator.persongenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class csv {
	
	public void toCSV(String[] data) throws FileNotFoundException {
		String today = LocalDate.now().toString();
		PrintWriter pw = new PrintWriter(new File("Immigration Test Data: " + today));
		StringBuilder sb = new StringBuilder();
		
	}
}

