package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import com.securiport.simulator.persongenerator.TimingGeneratorFactory.GeneratorType;

class MainProgram {
	private static TimingGenerator timeGenerator;

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 4) {
			System.out.println("Please provide start time, end time, number of entries, "
					+ "time generation type, and an optional seed");
		} else {
			// define variables that dont require check
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"); 
			LocalDateTime Start = null;
			LocalDateTime End = null;
			int Seed = 0;
			long Entries = 0;
			
			try {
				Start = LocalDateTime.parse(args[0], format);
				End = LocalDateTime.parse(args[1], format);
			} catch (Exception ex) {
				System.out.println("Dates must be in MM-dd-yyyy HH:mm:ss format");
			}
			
			try {
				Entries = Long.parseLong(args[2]);
			} catch (Exception ex) {
				System.out.println("Number of entries must be an integer");
			}
			
			try {
				Seed = Integer.parseInt(args[4]);
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
			

//			UniformTimingGenerator UTG = new UniformTimingGenerator();
//			UTG.initialize(Start, End, Entries);
			
			
			TimingGeneratorFactory timeFactory = new TimingGeneratorFactory();
			String timeMethod = args[3];
			if (timeMethod.equalsIgnoreCase("UNIFORM")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.UNIFORM);
			} else if (timeMethod.equalsIgnoreCase("EXPONENTIAL")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.EXPONENTIAL);
			} else if (timeMethod.equalsIgnoreCase("LOGISTIC")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.LOGISTIC);
			} else if (timeMethod.equalsIgnoreCase("LOGARITHMIC")) {
				timeGenerator = timeFactory.getGenerator(GeneratorType.LOGARITHMIC);
			} else {
				System.out.println("Invalid time generating type. Please choose from: uniform, exponential, logistic, "
						+ "or logarithmic");
			}
			
			timeGenerator.initialize(Start, End, Entries);

			System.out.println("UUID, Time of Entry, Full Name, Country of Origin, Date of Birth, Issue Date, Expiration Date, Passport Number");
			
			for ( int i=0; i<Entries; i++ ) {
				
				Passport passport = new Passport();
				String[] passportData = passport.getPassportData(R);
				
				String personEntered = timeGenerator.getNextTime() + " " + passportData[0] + " " + passportData[2] + " " + passportData[1]
						+ " " + passportData[3] + " " + passportData[4] + " " + passportData[5];
				String uuid = UUID.nameUUIDFromBytes(personEntered.getBytes()).toString();
				System.out.println(uuid + " " + personEntered);
			}
		}	
	}
}