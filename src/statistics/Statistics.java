package statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import entities.Entity;

/**
 * The Class Statistics.
 */
public class Statistics {
	/** The total. */
	private int total;
	
	/** The correct. */
	private int correct;
	
	/** The error. */
	private int error;
	
	/** The correct rate. */
	private double correctRate;
	
	/** The error rate. */
	private double errorRate;
	
	private HashMap<String, Entity> classified;
	private HashMap<String, Entity> CorrectClassified;
	
	
	/**
	 * Instantiates a new statistics.
	 */
	public Statistics(HashMap<String, Entity> classified,HashMap<String,Entity> file) {
		this.classified=classified;
		this.CorrectClassified = file;
		this.total = classified.size();
		this.correct = 0;
		this.error = 0;
		calculateStatistics();
		calcCorrectRate();
		calcErrorRate();
	}
	
	private void calculateStatistics() {
		for (Entry<String, Entity> entry : classified.entrySet()) {  // Itrate through hashmap
			String key = entry.getKey();
			Entity ent = entry.getValue();
			if(CorrectClassified.get(key)==ent) {
				addCorrect();
			} else {
				addError();
			}
		}
	}
	
	private void addCorrect() {
		this.correct++;
	}
	
	private void addError() {
		this.error++;
	}
	
	/**
	 * Calc correct rate.
	 */
	private void calcCorrectRate() {
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
	private void calcErrorRate() {
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
