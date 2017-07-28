package com.securiport.simulator.persongenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class mainprogramtest {

	String fNF = "/Users/Alexander/csv/first_names.csv";
	String lNF = "/Users/Alexander/csv/last_names.csv";
	String Start = "07-24-2017 06:00:00";
	String End = "07-25-2017 03:00:00";
	String Seed = "900107636";
	String Entries = "20";
	
	
	@Test
	public void mainTestSeeded() {
		String[] args = {fNF, lNF, Start, End, Entries};
		MainProgram.main(args);
	}
//	@Test
//	public void timeTest() {
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"); 
//		LocalDateTime D = LocalDateTime.parse(Start, format);
//		System.out.println(D);
//	}
	
//	@Test
//	public void mainTestNotSeeded() {
//		MainProgram.Main(fNF, lNF, Start, End, Entries);
//	}
}