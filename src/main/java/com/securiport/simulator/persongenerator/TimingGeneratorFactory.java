package com.securiport.simulator.persongenerator;

public class TimingGeneratorFactory {
	
	public enum GeneratorType {
		UNIFORM
	}
	
	public TimingGenerator getGenerator(GeneratorType type) throws IllegalArgumentException {
		
		if (type==GeneratorType.UNIFORM) {
			return null; 
		} else {
			IllegalArgumentException ex = new IllegalArgumentException("Invalid generator type passed");
			throw ex;
		}
	}

}
