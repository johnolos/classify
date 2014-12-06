package statistics;

import java.util.ArrayList;
import java.util.HashMap;

import entities.Entity;

public class StartProbability {
	private ArrayList<String> words;
	private ArrayList<Entity> entity;
	private HashMap<Entity, Double> startProb;
	private boolean newSentence;
	private int sentences;
	int other,person,time,organization,country;
	
	public StartProbability(ArrayList<String> w,ArrayList<Entity> e) {
		this.words=w;
		this.entity=e;
		startProb = new HashMap<Entity, Double>();
		newSentence=true;
		sentences=0;
		getNumbersToCalculate();
		calculate();
//		printProb();
	}
	
	private void printProb() {
		System.out.println("C: " + startProb.get(Entity.COUNTRY));
		System.out.println("O: " + startProb.get(Entity.OTHER));
		System.out.println("P: " + startProb.get(Entity.PERSON));
		System.out.println("T: " + startProb.get(Entity.TIME));
		System.out.println("ORG: " + startProb.get(Entity.ORGAINIZATION));
		
	}

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
		startProb.put(Entity.COUNTRY, prob.getCalculatedProbability());
	}

	private void getNumbersToCalculate() {
		for(int i=0; i<this.words.size();i++) {
//			System.out.println(words.size());
			if(newSentence) {
//				System.out.println("JEG ER HER"+i);
//				System.out.println(words.get(i));
				sentences++;
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
				case COUNTRY:
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

	private boolean isLastword(String word) {
		String temp = word.substring(word.length()-1, word.length());
//		System.out.println("temp: "+temp);
		if(temp.equals(".")) {
//			System.out.println("SHIT");
			return true;
		}
		return false;
	}
	
	public HashMap<Entity, Double> getStartProbability() {
		return this.startProb;
	}
}
