package statistics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import readTestData.ReadFile;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import entities.Entity;

public class TransmissionProbability {
	// Just a skeleton to give an example how the class will look.
	
	private Table<Entity, Entity, Float> transProb; // Transmission Probability
	private ReadFile file;
	
	public TransmissionProbability(String path) {
		file = new ReadFile(path);
		transProb = HashBasedTable.create();
	}
	
	public TransmissionProbability(ReadFile file) {
		this.file = file;
		transProb = HashBasedTable.create();
	}
	
	public void runTransmissionProbabilistic() {
		List<Entity> entity = file.getEntityList();
		List<String> words = file.getWordList();
		List<Entity> states = Arrays.asList(Entity.values());
		for(int i = 0; i < entity.size(); i++) {
			System.out.println("Word " + words.get(i) + " ,Entity " + entity.get(i));
		}
		for(Entity state : states) {
			for(Entity other: states) {
				/**
				 * TODO:	Paper says not to do probability for compare 
				 * 			state == other.
				 * 			It seems like they do anyway.
				 */
				float prob = findEntityCombinationFreq(state, other, entity);
				transProb.put(state, other, prob);
			}
		}
	}
	
	public float findEntityCombinationFreq(Entity first, Entity second, List<Entity> list) {
		Iterator itr = list.iterator();
		int freq = 0;
		int total = 0;
		Entity prev = null;
		Entity current = (Entity)itr.next();
		while(itr.hasNext()) {
			prev = current;
			current = (Entity)itr.next();
			if(prev.equals(first)) {
				total++;
				if(current.equals(second)) {
					freq++;
				}
			}
		}
		if(total == 0)
			return (float)0.0;
		return (float)freq / (float)total;
	}
	
	public Table<Entity, Entity, Float> getTable() {return this.transProb;}
	public ReadFile getFile() {return this.file;}
	
	/**
	Test the code
	public static void main(String[] args) {
		TransmissionProbability trans = new TransmissionProbability("classification.txt");
		trans.runTransmissionProbabilistic();
		float frequency = trans.getTable().get(Entity.PERSON, Entity.PERSON);
		System.out.println(frequency);
	}
	**/
}