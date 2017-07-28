package com.securiport.simulator.persongenerator;

public abstract class BaseNameGenerator implements NameGenerator {
	
	protected String firstNameFile;
	protected String lastNameFile;
	
	public abstract String getName(RandomSeed R);
		
	/**
	 * firstName and lastName must be paths to .csv files
	 */
	
	public void initialize(String firstNameFile, String lastNameFile) {
		String csv = "csv";
		if (!firstNameFile.substring(firstNameFile.length()-3).equals(csv) || !lastNameFile.substring(lastNameFile.length()-3).equals(csv)) {
			throw new IllegalArgumentException("Files must be in csv format");
		}
		this.firstNameFile = firstNameFile;
		this.lastNameFile = lastNameFile;
	}
}