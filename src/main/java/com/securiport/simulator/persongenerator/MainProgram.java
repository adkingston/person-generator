package com.securiport.simulator.persongenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import com.securiport.simulator.persongenerator.timegenerator.TimingGenerator;
import com.securiport.simulator.persongenerator.timegenerator.TimingGeneratorFactory;
import com.securiport.simulator.persongenerator.timegenerator.TimingGeneratorFactory.GeneratorType;

/**
 * 
 * @author akingston
 *
 */

class MainProgram {
	private static TimingGenerator TIME_GENERATOR;

	public static int getSeed() {
		// Generates seed if none is given 
		Long M = System.currentTimeMillis();
		String F = Long.toString(M);
		int S = Integer.parseInt(F.substring(F.length()-8));
		return S;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * Program generates information regarding a traveler going through an airport border crossing. 
		 * 	Data generated includes: (Items not separated by blank line are calculated together)
		 * 	Items below person details are collected within Passport.java
		 * 
		 * 		UUID
		 * 
		 * 		FlightGenerator number					Relevant files: Airlines.java, FlightGenerator.java
		 * 		FlightGenerator origin country					
		 * 	
		 * 		Time of entry					R.F.: TimingGeneratorFactory.java, TimingGenerator.java, BaseTimingGenerator.java,							
		 * 											  FuntionalTiming Generator.java, UniformTimingGenerator.java
		 * 		Person details:
		 * 			Title						R.F.: names.json, NameData.java, NameDataReader.java, NameGenerator.java,
		 * 			First name						  BaseNameGenerator.java
		 * 			Last name
		 * 			Gender
		 * 
		 * 		Nationality						R.F.: cityandAgeData.json, World.java, Continent.java, Regions.java, Countries.java,
		 * 		Country of Birth					  AgeNationalityGenerator.java
		 * 		Traveler's final destination
		 * 		City of Birth
		 * 		Date of Birth
		 *
		 * 		Passport issue date				R.F.: Passport.java
		 * 		Passport expiration date	
		 * 		Passport number
		 */
		
		
//		 want to see how long this takes to run:
		 long startTime = System.nanoTime();
		
		if (args.length < 4) { // check if there are enough arguments 
			System.out.println("Please provide start time, end time, number of entries, "
					+ "time generation type, and an optional seed");
		} else {
			// define variables that don't require check
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"); 
			LocalDateTime Start = null;
			LocalDateTime End = null;
			int seedValue = 0;
			long Entries = 0;
			
			try { // convert dates to Local Date Time format. 
				Start = LocalDateTime.parse(args[0], format);
				End = LocalDateTime.parse(args[1], format);
			} catch (Exception ex) {
				System.out.println("Dates must be in MM-dd-yyyy HH:mm:ss format");
			}
			
			try { // convert number of entries to integer. 
				Entries = Long.parseLong(args[2]);
			} catch (Exception ex) {
				System.out.println("Number of entries must be an integer");
			}
			
			// Initiate random number generator with appropriate seed 
			try { 
				seedValue = Integer.parseInt(args[4]);
			} catch (ArrayIndexOutOfBoundsException ind) { 
				seedValue = getSeed();
			} catch (Exception ex) {
				System.out.println("Seed input must be an integer"); // if seed is given, make sure it is an integer! 
			}
			RandomSeed seed = new RandomSeed(seedValue);
			
			//Generate timing mechanisms
			TimingGeneratorFactory timeFactory = new TimingGeneratorFactory();
			String timeMethod = args[3];
			if (timeMethod.equalsIgnoreCase("UNIFORM")) {
				TIME_GENERATOR = timeFactory.getGenerator(GeneratorType.UNIFORM);
			} else if (timeMethod.equalsIgnoreCase("EXPONENTIAL")) {
				TIME_GENERATOR = timeFactory.getGenerator(GeneratorType.EXPONENTIAL);
			} else if (timeMethod.equalsIgnoreCase("LOGISTIC")) {
				TIME_GENERATOR = timeFactory.getGenerator(GeneratorType.LOGISTIC);
			} else if (timeMethod.equalsIgnoreCase("LOGARITHMIC")) {
				TIME_GENERATOR = timeFactory.getGenerator(GeneratorType.LOGARITHMIC);
			} else {
				System.out.println("Invalid time generating type. Please choose from: uniform, exponential, logistic, "
						+ "or logarithmic");
			}
			// Initialize the timing generator
			TIME_GENERATOR.initialize(Start, End, Entries);

			// Create file to write generated data 
			PrintWriter pw = new PrintWriter(new File("Immigration Test Data " + seedValue + ".csv"));
			StringBuilder sb = new StringBuilder();
			
			// Write column titles
			sb.append("UUID"); 
			sb.append(',');
			sb.append("FlightGenerator No.");
			sb.append(',');
			sb.append("Departure Country");
			sb.append(',');
			sb.append("Final Destination");
			sb.append(',');
			sb.append("Time of Entry");
			sb.append(',');
			sb.append("Title");
			sb.append(',');
			sb.append("Given Name");
			sb.append(',');
			sb.append("Surname");
			sb.append(',');
			sb.append("Sex");
			sb.append(',');
			sb.append("Nationality");
			sb.append(',');
			sb.append("Country of Birth");
			sb.append(',');
			sb.append("POB");
			sb.append(',');
			sb.append("DOB");
			sb.append(',');
			sb.append("Issue Date");
			sb.append(',');
			sb.append("Expiration Date");
			sb.append(',');
			sb.append("Passport No.");
			sb.append('\n');
			
			/**
			 * Start with max of 2 FlightGenerators landed at the same time. Will look into how to generalize. 
			 * Ultimately, want to have FlightGenerators arriving on a schedule, so there could be multiple FlightGenerators 
			 * 	going through immigration at a single time. 
			 */
			FlightGenerator Flight1 = new FlightGenerator();	// Initialize the FlightGenerators
			FlightGenerator Flight2 = new FlightGenerator();
			ArrayList<String> Flight1Info = Flight1.getFlightInfo(seed);
			ArrayList<String> Flight2Info = Flight2.getFlightInfo(seed);
			
			ArrayList<Integer> passengerRange = new ArrayList<Integer>(Arrays.asList(50, 150));
			int r1 = seed.randRange(passengerRange); // Generate number of passengers (currently between 50 and 150). 
			int r2 = seed.randRange(passengerRange);
			
			int connectionSize = seed.randRange(passengerRange);
			
			int count1 = 0; // Use count to ensure that only the number of passengers on a FlightGenerator go through immigration with that FlightGenerator no. 
			int count2 = 0; 
			
			NationGenerator Nations = new NationGenerator(); // Get airport country name (currently, this is chosen at random). 
			String thisCountry = Nations.getCountry(seed).getCountryName();
			
			TravellerJourney onwards = new TravellerJourney();
			Map<String, Integer> connections = onwards.onwardTo(seed); // Can't have every passenger connecting to a different FlightGenerator.
																 // Generate list of 7-10 countries travelers can fly on to. 
			
			// Each passenger is generated independently from the rest. Issues: there are no groups, families, etc. Very unrealistic. 
			for ( int i=0; i<Entries; i++ ) {
				
				// Generate individual's passport and initiate their itinerary. 
				PassportDataGenerator passportGenerator = new PassportDataGenerator();
				TravellerJourney traveller = new TravellerJourney();
				ArrayList<String> passportData = passportGenerator.getPassportData(seed);
				
				// Check if either FlightGenerator has been completely processed. If either has, create a new FlightGenerator.
				if (count1 == r1) {
					Flight1Info = Flight1.getFlightInfo(seed);
					r1 = seed.randRange(passengerRange);
					count1 = 0;
				} else if (count2 == r2) {
					Flight2Info = Flight2.getFlightInfo(seed);
					r2 = seed.randRange(passengerRange);
					count2 = 0;
				}
				
				// Initiate FlightGenerator detail variables. 
				String FlightNo;
				String departureCountry;
				
				// Determine which FlightGenerator this passenger was on. Equal probability.
				if (seed.nextDouble() <= 0.5) {
					count1++;
					FlightNo = Flight1Info.get(0);
					departureCountry = Flight1Info.get(1);
				} else {
					count2++;
					FlightNo = Flight2Info.get(0);
					departureCountry = Flight2Info.get(1);
				}
				
				String finalDestination; // Ominous variable name choice... 
				
				// Determine if traveler is connecting. Get final destination country. 
				if (traveller.isConnecting(seed)) {
					finalDestination = traveller.getConnection(connections, seed);
					traveller.passengerBoarded(connections, finalDestination);
					if (connections.get(finalDestination) == connectionSize) { // once the connecting FlightGenerator is full, it departs and a new FlightGenerator starts. 
						traveller.connectionDeparted(connections, finalDestination, seed);
						connectionSize = seed.randRange(passengerRange);
					} else {
						/* do nothing as the FlightGenerator isn't full yet */
					}
				} else {
					finalDestination = thisCountry;
				}
				
				// Rename individual details for clarity
				String title = passportData.get(0);
				String firstName = passportData.get(1);
				String lastName = passportData.get(2);
				String gender = passportData.get(3);
				String DOB = passportData.get(4);
				String POB = passportData.get(5);
				String nationality = passportData.get(7);
				String CO = passportData.get(6);
				String issued = passportData.get(8);
				String expired = passportData.get(9);
				String passNo = passportData.get(10);
	
				// Determine time of entry
				LocalDateTime timeStamp = TIME_GENERATOR.getNextTime();
				
				// Given passport data as string will provide a UUID 
				String identifier = String.join(",", passportData);
				String uuid = UUID.nameUUIDFromBytes(identifier.getBytes()).toString();
			
				// Add details to string as appropriate 
				sb.append(uuid); 
				sb.append(',');
				sb.append(FlightNo);
				sb.append(',');
				sb.append(departureCountry);
				sb.append(',');
				sb.append(finalDestination);
				sb.append(',');
				sb.append(timeStamp);
				sb.append(',');
				sb.append(title);
				sb.append(',');
				sb.append(firstName);
				sb.append(',');
				sb.append(lastName);
				sb.append(',');
				sb.append(gender);
				sb.append(',');
				sb.append(nationality);
				sb.append(',');
				sb.append(CO);
				sb.append(',');
				sb.append(POB);
				sb.append(',');
				sb.append(DOB);
				sb.append(',');
				sb.append(issued);
				sb.append(',');
				sb.append(expired);
				sb.append(',');
				sb.append(passNo);
				sb.append('\n');
			}
			
			// Write completed string to the newly created csv file. (Move to SQL?)
			pw.write(sb.toString());
			pw.close();

			System.out.println("Done");
			
//			Again, to test run time. 			
			long endTime = System.nanoTime();
			System.out.println("Took " + (endTime-startTime) + " ns");
		}	
	}
}