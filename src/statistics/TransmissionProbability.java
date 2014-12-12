package statistics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import readData.ReadFileTraining;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class TransmissionProbability.
 */
public class TransmissionProbability {
	
	/** The trans prob. */
	private Table<Entity, Entity, Double> transProb; // Transmission Probability
	
	/** The file. */
	private ReadFileTraining file;
	
	/**
	 * Instantiates a new transmission probability.
	 *
	 * @param path the path
	 */
	public TransmissionProbability(String path) {
		file = new ReadFileTraining(path);
		transProb = HashBasedTable.create();
	}
	
	/**
	 * Instantiates a new transmission probability.
	 *
	 * @param file the file
	 */
	public TransmissionProbability(ReadFileTraining file) {
		this.file = file;
		transProb = HashBasedTable.create();
	}
	
	/**
	 * Run transmission probabilistic.
	 */
	public void runTransmissionProbabilistic() {
		List<Entity> entity = file.getEntityList();
		List<Entity> states = Arrays.asList(Entity.values());
		for(Entity state : states) {
			for(Entity other: states) {
				double prob = findEntityCombinationFreq(state, other, entity);
				transProb.put(state, other, prob);
			}
		}
	}
	
	/**
	 * Find entity combination freq.
	 *
	 * @param first the first
	 * @param second the second
	 * @param list the list
	 * @return the float
	 */
	public double findEntityCombinationFreq(Entity first, Entity second, List<Entity> list) {
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
			return (double)0.0;
		return (double)freq / (double)total;
	}
	
	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table<Entity, Entity, Double> getTable() {return this.transProb;}
	
	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public ReadFileTraining getFile() {return this.file;}
	
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