package com.securiport.simulator.persongenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.securiport.simulator.persongenerator.TimingGeneratorFactory.GeneratorType;

class MainProgram {
	private static TimingGenerator timeGenerator;

	public static void main(String[] args) {
		if (args.length < 5) {
			System.out.println("Please provide first name file, last name file, start time, end time, number of entries, "
					+ "time generation type, and an optional seed");
		} else {
			String firstNameFile = args[0];
			String lastNameFile = args[1];
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"); 
			
			LocalDateTime Start = null;
			LocalDateTime End = null;
			int Seed = 0;
			long Entries = 0;
			
			try {
				Start = LocalDateTime.parse(args[2], format);
				End = LocalDateTime.parse(args[3], format);
			} catch (Exception ex) {
				System.out.println("Dates must be in MM-dd-yyyy HH:mm:ss format");
			}
			
			try {
				Entries = Long.parseLong(args[4]);
			} catch (Exception ex) {
				System.out.println("Number of entries must be an integer");
			}
			
			try {
				Seed = Integer.parseInt(args[6]);
			} catch (ArrayIndexOutOfBoundsException ind) { 
				/* do nothing */ 
			} catch (Exception ex) {
				System.out.println("Seed input must be an integer");
			}
			
			RandomSeed R;
			if (Seed == 0) {
				R = new RandomSeed();
			} else {
				R = new RandomSeed(Seed);
			}
			
			
			NameDataReader N = new NameDataReader();
			N.initialize(firstNameFile, lastNameFile);
			
//			UniformTimingGenerator UTG = new UniformTimingGenerator();
//			UTG.initialize(Start, End, Entries);
			
			
			TimingGeneratorFactory timeFactory = new TimingGeneratorFactory();
			if (args[5].equalsIgnoreCase("UNIFORM")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.UNIFORM);
			} else if (args[5].equalsIgnoreCase("EXPONENTIAL")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.EXPONENTIAL);
			} else if (args[5].equalsIgnoreCase("LOGISTIC")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.LOGISTIC);
			} else if (args[5].equalsIgnoreCase("LOGARITHMIC")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.LOGARITHMIC);
			} else {
				System.out.println("Invalid time generating type. Please choose from: uniform, exponential, logistic, "
						+ "or logarithmic");
			}
			
			timeGenerator.initialize(Start, End, Entries);
			
			for ( int i=0; i<Entries; i++ ) {
				String personEntered = timeGenerator.getNextTime() + " " + N.getName(R);
				String uuid = UUID.nameUUIDFromBytes(personEntered.getBytes()).toString();
				System.out.println(uuid + " " + personEntered);
			}
		}	
	}
}