package viterbi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import statistics.EmissionProbability;
import statistics.TransmissionProbability;

import com.google.common.collect.Table;

import entities.Entity;

public class Viterbi {
	
	List<Entity> states; // States
	List<String> obs; // Observations
	Map<Entity, Float> initProb; // Initial Probability
	TransmissionProbability transProb;
	EmissionProbability emiProb;
//	Table<Entity, Entity, Float> transProb; // Transmission Probability
//	Table<Entity, String, Float> emiProb; // Emission Probability
	
	
	Entity prevState;
	
	// Need to debate which type to represent each input.
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
	
	public void run() {
		for(Entity state : states) {
			System.out.println(state);
		}
		
	}
	
	public static void main(String[] args) {
		Viterbi viterbi = new Viterbi(null, null, null, null);
		viterbi.run();
	}
		
}
