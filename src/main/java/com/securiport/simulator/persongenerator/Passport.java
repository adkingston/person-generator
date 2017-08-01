package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


public class Passport {
	
	private static String ageData = "C:/Users/Alexander/csv/ageData.json";
	
	public String[] getIssueDate(int age, RandomSeed R) {
		String[] issueExpirey = new String[2];
		LocalDate now = LocalDate.now();
		LocalDate lastEntryValid = now.minusMonths(6);
		int validFor;
		if ( age <= 16 ) {
			validFor = 5;
		} else {
			validFor = 10;
		}
		LocalDate firstEntryValid = now.minusYears(validFor);
		int daysBetween = (int) ChronoUnit.DAYS.between(firstEntryValid, lastEntryValid);
		LocalDate issueDate = firstEntryValid.plusDays(R.getNum(daysBetween));
		LocalDate expireyDate = issueDate.plusYears(validFor);
		issueExpirey[0] = issueDate.toString();
		issueExpirey[1] = expireyDate.toString();
		return issueExpirey;
	}
	
	public String generateNumber(RandomSeed R) {
		String number = new String();
		for (int i=0; i<9; i++) {
			number += Integer.toString(R.getNum(10));
		}
		return number;
	}
	
	public String[] getPassportData(RandomSeed R) throws FileNotFoundException {
		String[] data = new String[6];
		
		// first get country of origin
		AgeNationalityGenerator aN = new AgeNationalityGenerator();
		World world = aN.parseJson(ageData);
		Continent continent = aN.nextContinent(world, R);
		Regions region = aN.nextRegion(continent, R);
		Countries countryOfOrigin = aN.nextCountry(region, R);
		
		String CO = countryOfOrigin.getCountryName();
		int age = aN.nextAge(countryOfOrigin.getAges(), R);
		String DOB = aN.getDOB(age, R).toString();
		
		String[] issueAndExpireyDates = getIssueDate(age, R);
		
		NameDataReader N = new NameDataReader();
		N.initialize("C:/Users/Alexander/csv/first_names.csv", "C:/Users/Alexander/csv/last_names.csv");
		
		String name = N.getName(R);
		
		data[0] = name;
		data[1] = DOB;
		data[2] = CO;
		for (int i=0; i<2; i++) {
			data[i+3] = issueAndExpireyDates[i];
		}
		data[5] = generateNumber(R);
		
		return data;
	}
	
}