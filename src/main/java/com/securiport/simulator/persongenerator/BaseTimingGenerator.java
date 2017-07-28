package com.securiport.simulator.persongenerator;

import java.time.LocalDateTime;

import com.securiport.simulator.persongenerator.FunctionalTimeGenerator.functionalType;

public abstract class BaseTimingGenerator implements TimingGenerator {
	
	protected LocalDateTime minimumTime;
	protected LocalDateTime maximumTime;
	protected long numberToGenerate;
	protected functionalType type;

	public abstract LocalDateTime getNextTime();
	
	/**
	 * @param minimumTime the minimumTime to set.  Must be non-null.
	 * @param maximumTime the maximumTime to set.  Must be non-null and after minimumTime
	 * @param numberToGenerate the maximum number of times to generate.  Must be greater than zero. 
	 */
	public void initialize(LocalDateTime minimumTime, LocalDateTime maximumTime, long numberToGenerate) {
		if (minimumTime == null || maximumTime == null) {
			throw new IllegalArgumentException("Arguments cannot be null");
		} else if (minimumTime.isAfter(maximumTime) ) {
			throw new IllegalArgumentException("minimumTime must be before maximumTime");			
		} else if (numberToGenerate <= 0) {
			throw new IllegalArgumentException("numberToGenerate must be greater than zero");			
		}
		this.minimumTime = minimumTime;
		this.maximumTime = maximumTime;
		this.numberToGenerate = numberToGenerate;
	}
	
}
