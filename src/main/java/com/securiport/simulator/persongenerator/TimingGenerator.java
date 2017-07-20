package com.securiport.simulator.persongenerator;

import java.time.LocalDateTime;

public interface TimingGenerator {
	
	/**
	 * @return LocalDateTime that represents the next timed event based on the strategy
	 */
	LocalDateTime getNextTime();
	
	void initialize(LocalDateTime minimumTime, LocalDateTime maximumTime, long numberToGenerate);
	
}
