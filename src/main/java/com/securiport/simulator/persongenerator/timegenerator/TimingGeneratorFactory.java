package com.securiport.simulator.persongenerator.timegenerator;

public class TimingGeneratorFactory {
	
	public enum GeneratorType {
		UNIFORM, EXPONENTIAL, LOGISTIC, LOGARITHMIC;
	}
	
	public TimingGenerator getGenerator(GeneratorType type) throws IllegalArgumentException {
		
		if (type==GeneratorType.UNIFORM) {
			return new UniformTimingGenerator();
		} else if (type==GeneratorType.EXPONENTIAL) {
			return new FunctionalTimeGenerator(FunctionalTimeGenerator.functionalType.EXPONENTIAL);
		} else if (type==GeneratorType.LOGISTIC) {
			return new FunctionalTimeGenerator(FunctionalTimeGenerator.functionalType.LOGISTIC);
		} else if (type==GeneratorType.LOGARITHMIC) {
			return new FunctionalTimeGenerator(FunctionalTimeGenerator.functionalType.LOGARITHMIC);
		} else {
			IllegalArgumentException ex = new IllegalArgumentException("Invalid generator type passed");
			throw ex;
		}
	}

}
