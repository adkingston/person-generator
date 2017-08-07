package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;



public class Passport {
	
	public boolean isExpat(RandomSeed R) {
		double r = R.nextDouble();
		return r <= 0.25; 
	}
	
	public ArrayList<String> getIssueDate(LocalDate DOB, RandomSeed R) {
		/**
		 * Generates passport issue date and expiration data. Age is used to determine duration of validity. 
		 */
		ArrayList<String> issueExpire = new ArrayList<String>();
		LocalDate now = LocalDate.now();
		int age = now.getYear() - DOB.getYear(); // roughly determine age
		LocalDate lastEntryValid = now.minusMonths(6); // Most countries wont allow travel if documents expire within 6 months.
		int validFor; // number of years for which document is valid is generally different between adults and minors. 
		if ( age <= 16 ) {
			validFor = 5;
		} else {
			validFor = 10;
		}
		
		LocalDate firstEntryValid; // Determine the earliest possible date that documents can be valid
		if (validFor > age) {
			firstEntryValid = DOB.plusMonths(3); // Add three months to DOB - processing time
		} else {
			firstEntryValid = now.minusYears(validFor).plusMonths(6); // otherwise the earliest possible issue date is the duration of validity minus 6 months. 
		}
		int daysBetween = (int) ChronoUnit.DAYS.between(firstEntryValid, lastEntryValid); // Should be self-explanatory
		LocalDate issueDate = firstEntryValid.plusDays(R.getNum(daysBetween));
		LocalDate expireyDate = issueDate.plusYears(validFor);
		
		issueExpire.add(issueDate.toString());
		issueExpire.add(expireyDate.toString());
		return issueExpire;
	}
	
	public String generateNumber(RandomSeed R) {
		// generates a string of 9 numbers
		String number = new String();
		number += Integer.toString(R.getNum(9) + 1); // first number cannot be 0
		for (int i=0; i<8; i++) {
			number += Integer.toString(R.getNum(10));
		}
		return number;
	}
		
	public ArrayList<String> getPassportData(RandomSeed R) throws FileNotFoundException {
		// Initialize necessary methods (or whatever the appropriate term for these things are... [types]?)
		ArrayList<String> data = new ArrayList<String>();
		NationGenerator N = new NationGenerator();
		AgeGenerator A = new AgeGenerator();
		
		// first get country of origin
		Countries Nationality = N.getCountry(R);
		Countries countryOfBirth = null;

		if (isExpat(R)) {
			countryOfBirth = N.getCountry(R);
		} else { 
			countryOfBirth = Nationality;
		}
		
		ArrayList<String> cities = countryOfBirth.getCities();
		String CO = Nationality.getCountryName();
		String city = null;
		try { // some countries don't have city data: in this case, simply use the country name as POB
			int r = R.getNum(cities.size()-1);
			city = cities.get(r);
		} catch (Exception e) {
			city = countryOfBirth.getCountryName();
		}

		int age = A.nextAge(countryOfBirth.getAges(), R);
		if (age == 0) {
			age++;// no newborns!! 
		}
		
		LocalDate DOB = A.getDOB(age, R);
		
		ArrayList<String> issueAndExpireyDates = getIssueDate(DOB, R);
		
		NameDataReader nameData = new NameDataReader();
		nameData.initialize("dataFiles/names.json");
		ArrayList<String> name = nameData.getName(R);

		data.addAll(name);
		data.add(DOB.toString());
		data.add(city);
		data.add(countryOfBirth.getCountryName());
		data.add(CO);
		data.addAll(issueAndExpireyDates);
		data.add(generateNumber(R));
	
		return data;	
	}
}