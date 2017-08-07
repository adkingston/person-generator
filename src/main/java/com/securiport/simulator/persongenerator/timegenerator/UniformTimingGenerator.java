package com.securiport.simulator.persongenerator.timegenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UniformTimingGenerator extends BaseTimingGenerator implements TimingGenerator {
	
	private LocalDateTime lastTime = null;
	private Duration increment = null;
	private long counter = 0;
	
	@Override
	public LocalDateTime getNextTime() {
		
		if (lastTime == null) {			
			lastTime = this.minimumTime;
			increment = Duration.ofMillis(ChronoUnit.MILLIS.between(minimumTime, maximumTime) / numberToGenerate);
			counter++;
			return(lastTime);
		} else if (lastTime.isAfter(maximumTime)) {
			return(null);
		} else if (counter >= numberToGenerate) {
			return(null);
		}
		
		lastTime = lastTime.plus(increment);
		counter++;
		return(lastTime);
	}

}
