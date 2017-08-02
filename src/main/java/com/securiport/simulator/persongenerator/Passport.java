package com.securiport.simulator.persongenerator;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;


public class Passport {
	
	private static String ageData = "C:/Users/Alexander/csv/ageData.json";
	
	public ArrayList<String> getIssueDate(int age, RandomSeed R) {
		ArrayList<String> issueExpirey = new ArrayList<String>();;
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
		
		issueExpirey.add(issueDate.toString());
		issueExpirey.add(expireyDate.toString());
		return issueExpirey;
	}
	
	public String generateNumber(RandomSeed R) {
		String number = new String();
		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1, 10));
		number += Integer.toString(R.randRange(A)); // first number cannot be 0
		for (int i=0; i<8; i++) {
			number += Integer.toString(R.getNum(10));
		}
		return number;
	}
	
	public ArrayList<String> getPassportData(RandomSeed R) throws FileNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		
		// first get country of origin
		AgeNationalityGenerator aN = new AgeNationalityGenerator();
		World world = aN.parseJson(ageData);
		Continent continent = aN.nextContinent(world, R);
		Regions region = aN.nextRegion(continent, R);
		Countries countryOfOrigin = aN.nextCountry(region, R);
		
		String CO = countryOfOrigin.getCountryName();
		int age = aN.nextAge(countryOfOrigin.getAges(), R);
		String DOB = aN.getDOB(age, R).toString();
		
		ArrayList<String> issueAndExpireyDates = getIssueDate(age, R);
		
		NameDataReader N = new NameDataReader();
		N.initialize("C:/Users/Alexander/csv/names.json");
		
		ArrayList<String> name = N.getName(R);

		data.addAll(name);
//		for (int i=0; i<4; i++) {
//			data.add(name.get(i));
//		}
		data.add(DOB);
		data.add(CO);
		data.addAll(issueAndExpireyDates);
//		for (int i=0; i<2; i++) {
//			data[i+6] = issueAndExpireyDates[i];
//		}
		
		data.add(generateNumber(R));
		
		
		return data;
	}
	
}