package statistics;

import java.util.HashMap;

import entities.Entity;

/**
 * The Class Statistics.
 */
public class Statistics {
	//THIS CLASS NEEDS FIXING BUT THIS IS A FRAME
	/** The total. */
	int total;
	
	/** The correct. */
	int correct;
	
	/** The error. */
	int error;
	
	/** The correct rate. */
	double correctRate;
	
	/** The error rate. */
	double errorRate;
	
	/**
	 * Instantiates a new statistics.
	 */
	public Statistics() {
		this.total = 0; //FIX
		this.correct = 0; //FIX
		this.error = 0; //FIX
	}
	
	/**
	 * Calc correct rate.
	 */
	public void calcCorrectRate() {
		this.correctRate = this.correct/this.total;
	}
	
	/**
	 * Gets the correct rate.
	 *
	 * @return the correct rate
	 */
	public double getCorrectRate() {
		return this.correctRate;
	}
	
	/**
	 * Calc error rate.
	 */
	public void calcErrorRate() {
		this.errorRate = this.error/this.total;
	}
	
	/**
	 * Gets the error rate.
	 *
	 * @return the error rate
	 */
	public double getErrorRate() {
		return this.errorRate;
	}
}
