package statistics;

import java.util.ArrayList;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import entities.Entity;

/**
 * The Class EmissionProbability.
 */
public class EmissionProbability {
	
	/** The entity. */
	private ArrayList<Entity> entity;
	
	/** The words. */
	private ArrayList<String> words;
	
	/** The emi prob. */
	private Table<Entity, String, Double> emiProb; // Transmission Probability
	
	/** The calc. */
	private CalculateProbablity calc;
	
	/**
	 * Instantiates a new emission probability.
	 *
	 * @param entity the entity
	 * @param words the words
	 */
	public EmissionProbability(ArrayList<Entity> entity,ArrayList<String> words) {
		this.entity = entity;
		this.words = words;
		this.emiProb = HashBasedTable.create();
		this.calc = new CalculateProbablity();
		calc.setTotal(words.size());
//		System.out.println("TOTAL; " + words.size());
		calculateEmission();
//		printEmission();
	}
	
	
	/**
	 * Prints the emission.
	 */
	private void printEmission() {
		System.out.println(emiProb.row(Entity.ORGAINIZATION));
		System.out.println(emiProb.row(Entity.COUNTRY));
		System.out.println(emiProb.row(Entity.OTHER));
		System.out.println(emiProb.row(Entity.PERSON));
		System.out.println(emiProb.row(Entity.TIME));
//		System.out.println("M: " + emiProb.get(Entity.ORGAINIZATION, "microsoft"));
//		System.out.println("H: " + emiProb.get(Entity.OTHER, "her"));
		
	}


	/**
	 * Calculate emission.
	 */
	private void calculateEmission() {
		int count=0;
		Entity ent = entity.get(0);
		String word = words.get(0);
		for(int i=0;i<this.words.size();i++) {
			count=1;
			ent = entity.get(i);
			word = words.get(i).toLowerCase();
			if(isLastword(word))
				word = removeDot(word);
//			System.out.println("i: " + word);
			for(int j=i+1;j<this.words.size();j++) {
				String secondWord = words.get(j).toLowerCase();
				if(isLastword(secondWord))
					secondWord = removeDot(secondWord);
//				System.out.println("j: " + secondWord);
				if(word.equals(secondWord)) {
//					System.out.println("COUNT:"+ count);
					count++;
				}	
			}
			calc.setCounter(count);
//			System.out.println("i: " + i);
//			printEmission();
			if(emiProb.get(ent, word) == null) {
				emiProb.put(ent, word, calc.getCalculatedProbability());				
			}
		}
	}


	/**
	 * Checks if is lastword.
	 *
	 * @param word the word
	 * @return true, if is lastword
	 */
	private boolean isLastword(String word) {
		String temp = word.substring(word.length()-1, word.length());
		if(temp.equals(".")) {
			return true;
		}
		return false;
	}


	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table<Entity, String, Double> getTable() {
		return this.emiProb;
	}
	
	/**
	 * Removes the dot.
	 *
	 * @param word the word
	 * @return the string
	 */
	private String removeDot(String word) {
		return word.substring(0, word.length()-1).trim();
	}
}
