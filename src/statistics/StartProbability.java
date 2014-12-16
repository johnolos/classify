package statistics;

import java.util.ArrayList;
import java.util.HashMap;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class StartProbability.
 */
public class StartProbability {
	
	/** The words. */
	private ArrayList<String> words;
	
	/** The entity. */
	private ArrayList<Entity> entity;
	
	/** The start prob. */
	private HashMap<Entity, Double> startProb;
	
	/** The new sentence. */
	private boolean newSentence;
	
	/** The sentences. */
	private int sentences;
	
	/** The country. */
	int other,person,time,organization,country;
	
	/**
	 * Instantiates a new start probability.
	 *
	 * @param words the words
	 * @param entity the entity
	 */
	public StartProbability(ArrayList<String> words,ArrayList<Entity> entity) {
		this.words=words;
		this.entity=entity;
		this.startProb = new HashMap<Entity, Double>();
		this.newSentence=true;
		this.sentences=0;
		getNumbersToCalculate();
		calculate();
//		printProb();
	}

	/**
	 * Prints the prob.
	 */
	private void printProb() {
		System.out.println("L: " + startProb.get(Entity.LOCATION));
		System.out.println("O: " + startProb.get(Entity.OTHER));
		System.out.println("P: " + startProb.get(Entity.PERSON));
		System.out.println("T: " + startProb.get(Entity.TIME));
		System.out.println("ORG: " + startProb.get(Entity.ORGAINIZATION));
		
	}

	/**
	 * Calculate.
	 */
	private void calculate() {
		CalculateProbablity prob = new CalculateProbablity();
		prob.setTotal(sentences);
		prob.setCounter(other);
//		System.out.println("OTHER" + other);
//		System.out.println("SENTENCES"+sentences);
//		System.out.println("PROB"+prob.getCalculatedProbability());
		startProb.put(Entity.OTHER, prob.getCalculatedProbability());
		prob.setCounter(person);
		startProb.put(Entity.PERSON,prob.getCalculatedProbability());
		prob.setCounter(time);
		startProb.put(Entity.TIME, prob.getCalculatedProbability());
		prob.setCounter(organization);
		startProb.put(Entity.ORGAINIZATION,prob.getCalculatedProbability());
		prob.setCounter(country);
		startProb.put(Entity.LOCATION, prob.getCalculatedProbability());
	}

	/**
	 * Gets the numbers to calculate.
	 *
	 * @return the numbers to calculate
	 */
	private void getNumbersToCalculate() {
		for(int i=0; i<this.words.size();i++) {
//			System.out.println(words.size());
			if(newSentence) {
				sentences++;
//				System.out.println("JEG ER HER"+i);
//				System.out.println(entity.get(i));
//				System.out.println(words.get(i));
				switch (entity.get(i)) {
				case PERSON:
//					System.out.println("P");
					person++;
					break;
				case TIME:
//					System.out.println("T");
					time++;
					break;
				case ORGAINIZATION:
//					System.out.println("ORG");
					organization++;
					break;
				case LOCATION:
//					System.out.println("C");
					country++;
					break;
				default:
//					System.out.println("O");
					other++;
				}
			}
			newSentence=false;
			if(isLastword(words.get(i))) {
				newSentence=true;
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
//		System.out.println("temp: "+temp);
		if(temp.equals(".")) {
//			System.out.println("SHIT");
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the start probability.
	 *
	 * @return the start probability
	 */
	public HashMap<Entity, Double> getStartProbability() {
		return this.startProb;
	}
}
