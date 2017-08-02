package com.securiport.simulator.persongenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
			
			String today = LocalDate.now().toString();
			PrintWriter pw = new PrintWriter(new File("Immigration Test Data " + today + ".csv"));
			StringBuilder sb = new StringBuilder();
			
			sb.append("UUID"); // Write column titles
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
			sb.append("Country of Origin");
			sb.append(',');
			sb.append("Date of Birth");
			sb.append(',');
			sb.append("Issue Date");
			sb.append(',');
			sb.append("Expiration Date");
			sb.append(',');
			sb.append("Passport Number");
			sb.append('\n');
			
			
			for ( int i=0; i<Entries; i++ ) {
				
				Passport passport = new Passport();
				String[] passportData = passport.getPassportData(R);
				
				String title = passportData[0];
				String firstName = passportData[1];
				String lastName = passportData[2];
				String gender = passportData[3];
				String DOB = passportData[4];
				String CO = passportData[5];
				String issued = passportData[6];
				String expired = passportData[7];
				String passNo = passportData[8];
				
				LocalDateTime timeStamp = timeGenerator.getNextTime();
				
				// Given passport data as string will provide a unique UUID 
				String personEntered = timeStamp + " " + title + " " + firstName + " " + lastName + " " + gender + " " + CO + " " + DOB
						+ " " + issued + " " + expired + " " + passNo;
				String uuid = UUID.nameUUIDFromBytes(personEntered.getBytes()).toString();
			
				
				sb.append(uuid);
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
				sb.append(CO);
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
			pw.write(sb.toString());
			pw.close();

			System.out.println("Done");
		}	
	}
}