package statistics;

import java.util.List;

import readTestData.ReadFile;

import com.google.common.collect.Table;

import entities.Entity;

public class TransmissionProbability {
	// Just a skeleton to give an example how the class will look.
	
	Table<Entity, Entity, Float> transProb; // Transmission Probability
	ReadFile file;
	
	public TransmissionProbability(String path) {
		file = new ReadFile(path);
	}
	
	public TransmissionProbability(ReadFile file) {
		this.file = file;
	}
	
	public void runTransmissionProbabilistic() {
		List<String> list = file.getWordList();
		List<Entity> entity = file.getEntityList();
		
	}
	
	public Table<Entity, Entity, Float> getTable() {return this.transProb;}

}
