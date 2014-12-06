package statistics;

import com.google.common.collect.Table;

import entities.Entity;

public class EmissionProbability {
	// Just a skeleton to give an example how the class will look.
	
	Table<Entity, String, Float> emiProb; // Transmission Probability
	
	public EmissionProbability() {
		
		
	}
	
	
	public Table<Entity, String, Float> getTable() {return this.emiProb;}
}
