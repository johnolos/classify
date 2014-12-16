package viterbi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import patterns.RegexPatterns;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import entities.Entity;

// TODO: Auto-generated Javadoc

/**
 * A class representation of the Viterbi algorithm which
 * can be ran to give estimations on Hidden Markov Model.
 */
public class Viterbi {
	
	public static final double MIN_VALUE = 0.0000001;
	private static final double KNOWLEDGE_INFLUENCE = -0.5;
	private static final double START_VALUE = -10000.0;
	
	/** The states. */
	List<Entity> states; // States
	
	/** The observations. */
	List<String> obs; // Observations
	
	/** The init prob. */
	Map<Entity, Double> initProb; // Initial Probability
	
	/** The transmission table probabilities */
	Table<Entity, Entity, Double> transProb;
	
	/** The emission table probabilities */
	Table<Entity, String, Double> emiProb;
	
	/** The prev state. */
	Entity prevState;
	
	List<Entity> probabilityStates;
	
	/** The prev table */
	Table<Entity, String, Double> prevTable;
	
	/** The current table */
	Table<Entity, String, Double> currentTable;
	
	/** Regex Patterns */
	RegexPatterns regex;
	
	
	/**
	 * Instantiates a new Viterbi object.
	 * This object requires a few inputs to run
	 * the Viterbi algorithm on a
	 * Hidden Markov Model.
	 *
	 * @param obs The observations. 
	 * This is a list of string in a sentence.
	 * @param transProb The Transmission Table.
	 * @param emiProb The Emission Table.
	 * @param initProb The Start Table.
	 */
	public Viterbi(
			List<String> obs, 
			Table<Entity, Entity, Double> transProb,
			Table<Entity, String, Double> emiProb, 
			Map<Entity, Double> initProb
			) {
		this.states = Arrays.asList(Entity.values());
		this.obs = obs;
		this.transProb = transProb;
		this.emiProb = emiProb;
		this.initProb = initProb;
		
		prevState = null;
		currentTable = null;
		probabilityStates = null;
		
		this.regex = new RegexPatterns();
	}
	
	/**
	 * Run the Viterbi Algorithm.
	 * @throws ObservationException 
	 */
	public void run() throws ObservationException {
		if(obs == null)
			throw new ObservationException("Insert new observations");
		probabilityStates = new ArrayList<Entity>();
		Entity regex;
		// Iterate over all observations. Words in the sentence.
		for(String word : obs) {
			currentTable = HashBasedTable.create();
			double maxValue = START_VALUE;
			Entity maxState = null;
			// Iterate over all states and calculate the probability of
			// the observation being in that state.
			for(Entity state : states) {
				// Runs calculations in delegated code
				double value = probability(state, word);
				if(value > maxValue) {
					maxValue = value;
					maxState = state;
				}
				// Put result in the table.
				currentTable.put(state, word, value);
			}
			if(maxState == Entity.OTHER) {
				// No result on classification so we check
				// our knowledge and Regex expressions.
				if((regex = checkRegexPatterns(word)) != null) {
					maxState = regex;
					System.out.println("Match " + word + maxState);
					maxValue = KNOWLEDGE_INFLUENCE;
					currentTable.put(maxState, word, maxValue);
				}
			}
			// Update our findings our array.
			probabilityStates.add(maxState);
			// Set current table as previous for next iteration.
			prevTable = currentTable;
		}
		obs = null;
	}
	
	
	
	/**
	 * Get the probability for state + word combination.
	 * It takes into account maximum likelihood for previous
	 * state.
	 * @param state State checked
	 * @param word String word checked.
	 * @return double probability value for this combination.
	 */
	private double probability(Entity state, String word) {
		double prob;
		if (prevTable == null) { // First time iteration
			double start = initProb.get(state) == 0.0 ? MIN_VALUE : initProb.get(state);
			double startValue = Math.log(start) / Math.log(2);
			double emi = emiProb.get(state, word) == null ? MIN_VALUE : emiProb.get(state, word);
//			if(emi > 0.0) System.out.println("Emi: " + emi + " State " + state + " Word " + word);
			double emiValue = Math.log(emi) / Math.log(2);
			prob = startValue + emiValue;
		} else { // Rest of the iterations
			double emi = emiProb.get(state, word) == null ? MIN_VALUE : emiProb.get(state, word);
//			if(emi > 0.0) System.out.println("Emi: " + emi  + " State " + state + " Word " + word);
			double emiValue = Math.log(emi) / Math.log(2);
			double maxValue = getMaximumTransitionProbability(state, word);
			prob = emiValue + maxValue;
//			if(emi > 0.0) System.out.println("Emivalue: " + emiValue + " MaxValue: " + maxValue + " Prob: " + prob);
		}
		return Math.pow(2, prob);
	}
	
	/**
	 * Get the maximum likelihood for transition between
	 * current state and previous state for a word.
	 * We only care about the value and not the
	 * combination that gives this.
	 * 
	 * Remember that all probabilities here
	 * are given in log2 for accuracy and efficiency.
	 * @param word Word checked
	 * @param currentState Current state checked
	 * @return double value that has the highest maximum probability
	 */
	private Double getMaximumTransitionProbability(Entity currentState,
			String word) {
		List<Double> values = new ArrayList<Double>();
		for(Entity state : states) {
			double prev = prevTable.get(state, word) == null ? MIN_VALUE : prevTable.get(state, word);
			double prevValue = Math.log(prev) / Math.log(2);
			double transValue = transProb.get(state, currentState) == 0.0 ? MIN_VALUE : transProb.get(state, currentState);
			double transitionValue = Math.log(transValue) / Math.log(2);
			values.add(prevValue + transitionValue);
		}
		return Collections.max(values);
	}
	
	/**
	 * Returns the current calculated table if the user
	 * want's to take a look.
	 * @return Table<Entity,String,Double>
	 */
	public Table<Entity, String, Double> getTable() {
		return currentTable;
	}
	
	/**
	 * Checks our knowledge about locations, common names
	 * and commonly used date patterns to see if we can
	 * classify the word based on that.
	 * @param text String word to be classified
	 * @return Entity Entity it fits.
	 */
	private Entity checkRegexPatterns(String text) {
		Entity plausible = null;
		String temp = text;
		if(Character.isUpperCase(temp.charAt(0))) {
			if(temp.contains("'")) {
				if(temp.contains("'s")) {
					temp = temp.replaceAll("'s", "");
				} else if(temp.contains("s'")) {
					temp = temp.replace("s'", "");
				}  else if(temp.contains("es'")) {
					temp = temp.replace("es'", "");
				}
			}
			if(regex.isName(temp)) {
				plausible = Entity.PERSON;
			}
			if(regex.isLocation(temp)) {
				plausible = Entity.LOCATION;
			}
		}
		if(regex.isTime(temp)) {
			plausible = Entity.TIME;
		}
		return plausible;
	}
	
	/**
	 * Get states the algorithm finds for each state.
	 * @return List<Entity> List of Entity states.
	 */
	public List<Entity> getStates() {
		return probabilityStates;
	}
	
	/**
	 * Set new observations for the Viterbi Algorithm
	 * to calculate the maximum likelihood of state for
	 * each of the observations.
	 * 
	 * @param obs List<String> List of observations
	 */
	public void setObservations(List<String> obs) {
		this.obs = obs;
		this.prevState = null;
		this.currentTable = null;
		this.probabilityStates = null;
		this.prevTable = null;
	}
	
	public class ObservationException extends Exception {
		  public ObservationException() { super(); }
		  public ObservationException(String message) { super(message); }
		  public ObservationException(String message, Throwable cause) { super(message, cause); }
		  public ObservationException(Throwable cause) { super(cause); }
	}
		
}
