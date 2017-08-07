package com.securiport.simulator.persongenerator;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;


public class FunctionalTimeGenerator extends BaseTimingGenerator implements TimingGenerator {

	private LocalDateTime LAST_TIME;
	private Duration INCREMENT;
	private Duration PARTITION_RANGE;
	private long COUNTER = 0;
	private static long TIME_RANGE;
	private functionalType _type;


	public enum functionalType {
		LOGISTIC, EXPONENTIAL, LOGARITHMIC;
		
		long calculate(double x) {
			switch (this) {
				case LOGISTIC :
					return  (long) ((long) TIME_RANGE*(Math.cos(Math.PI + Math.PI*x))*0.5 + 0.5);
				case EXPONENTIAL :
					return (long) ((long) TIME_RANGE*(x*x));
				case LOGARITHMIC :
					return (long) ((long) TIME_RANGE*(-x*x + 2*x));
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
	
	public Duration getINCREMENT(ArrayList<Duration> T) {
		ArrayList<LocalDateTime> Times = new ArrayList<LocalDateTime>();
		long a = this.minimumTime.atZone(ZoneId.systemDefault()).toEpochSecond();
		for ( int i=0; i<T.size(); i++ ) {
			double x = (double) T.get(i).toMillis();
			double y = x/TIME_RANGE;
			long l = _type.calculate(y) + a;
			Times.add(convertLocalDateTime(l));
		}
		return Duration.ofMillis(ChronoUnit.MILLIS.between(Times.get(0), Times.get(1)));
	}
	
	public LocalDateTime getNextTime() {
		if (LAST_TIME == null) {
			LAST_TIME = this.minimumTime;
			TIME_RANGE = ChronoUnit.MILLIS.between(this.minimumTime, this.maximumTime);
			PARTITION_RANGE = Duration.ofMillis(TIME_RANGE / this.numberToGenerate);
			COUNTER++;
			return LAST_TIME;
		} else if ( LAST_TIME.isAfter(this.maximumTime) ) {
			return null;
		} else if ( COUNTER >= this.numberToGenerate ) {
			return null;
		}
		ArrayList<Duration> Range = new ArrayList<Duration>(Arrays.asList(PARTITION_RANGE.multipliedBy(COUNTER), PARTITION_RANGE.multipliedBy(COUNTER+1)));
		INCREMENT = getINCREMENT(Range);
	
		LAST_TIME = LAST_TIME.plus(INCREMENT);
		COUNTER++;
		return LAST_TIME;
	}
}