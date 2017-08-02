package com.securiport.simulator.persongenerator;

public abstract class BaseNameGenerator implements NameGenerator {
	

	protected String NameFile;
	
	public abstract String[] getName(RandomSeed R);
		
	/**
	 * firstName and lastName must be paths to .csv files
	 */
	
	public void initialize(String NameFile) {
		String csv = "csv";
		if (!NameFile.substring(NameFile.length()-3).equals(csv)) {
			throw new IllegalArgumentException("Files must be in csv format");
		}
		this.NameFile = NameFile;

	}
}