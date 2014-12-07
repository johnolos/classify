package viterbi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import statistics.EmissionProbability;
import statistics.TransmissionProbability;

import com.google.common.collect.Table;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Viterbi.
 */
public class Viterbi {
	
	/** The states. */
	List<Entity> states; // States
	
	/** The obs. */
	List<String> obs; // Observations
	
	/** The init prob. */
	Map<Entity, Float> initProb; // Initial Probability
	
	/** The trans prob. */
	TransmissionProbability transProb;
	
	/** The emi prob. */
	EmissionProbability emiProb;
//	Table<Entity, Entity, Float> transProb; // Transmission Probability
//	Table<Entity, String, Float> emiProb; // Emission Probability
	
	
	/** The prev state. */
	Entity prevState;
	
	// Need to debate which type to represent each input.
	/**
	 * Instantiates a new viterbi.
	 *
	 * @param obs the obs
	 * @param transProb the trans prob
	 * @param emiProb the emi prob
	 * @param initProb the init prob
	 */
	public Viterbi(
//			List<Entity> states, 
			List<String> obs, 
			TransmissionProbability transProb,
			EmissionProbability emiProb, 
			Map<Entity, Float> initProb
			) {
		this.states = Arrays.asList(Entity.values());
//		this.states = states;
		this.obs = obs;
		this.transProb = transProb;
		this.emiProb = emiProb;
		this.initProb = initProb;
		
		prevState = null;
	}
	
	/**
	 * Run.
	 */
	public void run() {
		for(Entity state : states) {
			System.out.println(state);
		}
		
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
