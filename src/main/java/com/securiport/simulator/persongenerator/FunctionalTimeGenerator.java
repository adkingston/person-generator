package com.securiport.simulator.persongenerator;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


public class FunctionalTimeGenerator extends BaseTimingGenerator implements TimingGenerator {

	private LocalDateTime lastTime;
	private Duration increment;
	private Duration partitionRange;
	private long counter = 0;
	private static long timeRange;
	private functionalType _type;


	public enum functionalType {
		LOGISTIC, EXPONENTIAL, LOGARITHMIC;
		
		
		long calculate(double x) {
			switch (this) {
				case LOGISTIC :
					return  (long) ((long) timeRange*(Math.cos(Math.PI + Math.PI*x))*0.5 + 0.5);
				case EXPONENTIAL :
					return (long) ((long) timeRange*(x*x));
				case LOGARITHMIC :
					return (long) ((long) timeRange*(-x*x + 2*x));
				default :
					throw new AssertionError("Unknown function " + this);
			}
		}
	}
	
	public FunctionalTimeGenerator(FunctionalTimeGenerator.functionalType type) {
		super();
		_type = type;
	}
	
	public LocalDateTime convertLocalDateTime(long milliseconds) {
		Instant y = Instant.ofEpochMilli(milliseconds);
		return y.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public Duration getIncrement(Duration[] T) {
		LocalDateTime[] Times = new LocalDateTime[2];
		long a = this.minimumTime.atZone(ZoneId.systemDefault()).toEpochSecond();
		for ( int i=0; i<T.length; i++ ) {
			double x = (double) T[i].toMillis();
			double y = x/timeRange;
			long l = _type.calculate(y) + a;
			Times[i] = convertLocalDateTime(l);
		}
		return Duration.ofMillis(ChronoUnit.MILLIS.between(Times[0], Times[1]));
	}
	
	public LocalDateTime getNextTime() {
		if (lastTime == null) {
			lastTime = this.minimumTime;
			timeRange = ChronoUnit.MILLIS.between(this.minimumTime, this.maximumTime);
			partitionRange = Duration.ofMillis(timeRange / this.numberToGenerate);
			counter++;
			return lastTime;
		} else if ( lastTime.isAfter(this.maximumTime) ) {
			return null;
		} else if ( counter >= this.numberToGenerate ) {
			return null;
		}
		Duration[] Range = {partitionRange.multipliedBy(counter), partitionRange.multipliedBy(counter+1)};
		increment = getIncrement(Range);
	
		lastTime = lastTime.plus(increment);
		counter++;
		return lastTime;
	}
}