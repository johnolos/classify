package statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Statistics.
 */
public class Statistics {
	/** The total. */
	private double total;
	
	/** The correct. */
	private double correct;
	
	/** The error. */
	private double error;
	
	/** The correct rate. */
	private double correctRate;
	
	/** The error rate. */
	private double errorRate;
	
	/** The classified. */
	private HashMap<String, Entity> classified;
	
	/** The Correct classified. */
	private HashMap<String, Entity> CorrectClassified;
	
	private List<Entity> arrayClassified;
	
	
	/**
	 * Instantiates a new statistics.
	 *
	 * @param classified the classified
	 * @param file the file
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
	
	public Statistics(List<Entity> file, HashMap<String, Entity> classified) {
		this.arrayClassified = file;
		this.classified=classified;
		this.total = this.arrayClassified.size();
		this.correct=0;
		this.error=0;
		calcArrayHashStatistics();
		
	}
	
	private void calcArrayHashStatistics() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Calculate statistics.
	 */
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
	
	/**
	 * Adds the correct.
	 */
	private void addCorrect() {
		this.correct++;
	}
	
	/**
	 * Adds the error.
	 */
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
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public double getError() {
		return this.error;
	}
	
	/**
	 * Gets the correct.
	 *
	 * @return the correct
	 */
	public double getCorrect() {
		return this.correct;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return this.total;
	}
}