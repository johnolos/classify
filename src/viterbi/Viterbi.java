package viterbi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import statistics.EmissionProbability;
import statistics.TransmissionProbability;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import entities.Entity;

// TODO: Auto-generated Javadoc

/**
 * A class representation of the Viterbi algorithm which
 * can be ran to give estimations on Hidden Markov Model.
 */
public class Viterbi {
	
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
	
	/** The prev table */
	Table<Entity, String, Double> prevTable;
	
	/** The current table */
	Table<Entity, String, Double> currentTable;
	
	
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
	}
	
	/**
	 * Run the Viterbi Algorithm.
	 */
	public void run() {
		for(String word : obs) {
			currentTable = HashBasedTable.create();
			double maxValue = 0.0;
			Entity maxState = null;
			for(Entity state : states) {
				double value = probability(state, word);
				if(value > maxValue) {
					maxValue = value;
					maxState = state;
				}
				currentTable.put(state, word, value);
			}
			prevTable = currentTable;
		}
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
//		double prevValue = prevTable.get(state, word);
		double prob;
		if (prevTable == null) { // First time iteration
			double startValue = Math.log(initProb.get(state)) / Math.log(2);
			double emiValue = Math.log(emiProb.get(state, word)) / Math.log(2);
			prob = startValue + emiValue;
		} else { // Rest of the iterations
			double emiValue = Math.log(emiProb.get(state, word)) / Math.log(2);
			double maxValue = getMaximumTransitionProbability(state, word);
			prob = emiValue + maxValue;
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
			double prevValue = Math.log(prevTable.get(state, word)) / Math.log(2);
			double transitionValue = Math.log(transProb.get(state, currentState)) 
					/ Math.log(2);
			values.add(prevValue + transitionValue);
		}
		return Collections.max(values);
	}
	
	
	public Table<Entity, String, Double> getTable() {
		return currentTable;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Viterbi viterbi = new Viterbi(null, null, null, null);
		viterbi.run();
	}
		
}
