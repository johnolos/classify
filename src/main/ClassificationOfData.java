package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassificationOfData.
 */
public class ClassificationOfData {
	
	/** The words. */
	private ArrayList<String> words;
	
	/** The classified. */
	private HashMap<String,Entity> classified;
	
	/** The training map. */
	private HashMap<String, Entity> trainingMap;
	
	/** The start. */
	private HashMap<Entity,Double> start;
	
	/** The trans prob. */
	private Table<Entity, Entity, Float> transProb;
	
	/** The emission prob. */
	private Table<Entity,String, Double> emissionProb;
	
	/** The new sentence. */
	private boolean newSentence=true;
	
	/** The last entity. */
	private Entity lastEntity;
	
	/**
	 * Instantiates a new classification of data.
	 *
	 * @param words the words
	 * @param map the map
	 * @param start the start
	 * @param trans the trans
	 * @param emi the emi
	 */
	public ClassificationOfData(ArrayList<String> words,HashMap<String, Entity> map, HashMap<Entity,Double> start,Table<Entity, Entity, Float> trans,Table<Entity,String,Double> emi) {
		this.words=words;
		this.trainingMap=map;
		this.start=start;
		this.transProb=trans;
		this.emissionProb=emi;
		this.classified = new HashMap<String, Entity>();
		runClassify();
	}
	
	/**
	 * Run classify.
	 */
	public void runClassify() {
		for(int i=0;i<this.words.size();i++) {
			classify(this.words.get(i));
		}
	}
	
	/**
	 * Classify.
	 *
	 * @param word the word
	 */
	private void classify(String word) {
		System.out.println(word);
		String temp = word.toLowerCase();
		if(trainingMap.get(temp)!=null) {
			lastEntity=trainingMap.get(temp);
			classified.put(word, trainingMap.get(temp));
		} else if(newSentence) {
			newSentence=false;
			lastEntity=findMaxStart();
			classified.put(word, lastEntity);
		} else {
			Entity ent = getTransmisionEntity();
			classified.put(word, ent);
			lastEntity=ent;
		}
//		System.out.println("Classified: " + lastEntity);
	}
	
	/**
	 * Gets the transmision entity.
	 *
	 * @return the transmision entity
	 */
	private Entity getTransmisionEntity() {
		List<Entity> entity = Arrays.asList(Entity.values());
		float max = 0;
		Entity ent = lastEntity;
		for(int i=0;i<entity.size();i++) {
			if(transProb.get(lastEntity, entity.get(i)).floatValue()>max) {
				max=transProb.get(lastEntity, entity.get(i)).floatValue();
				ent = entity.get(i);
			}
		}
		return ent;
	}

	/**
	 * Find max start.
	 *
	 * @return the entity
	 */
	private Entity findMaxStart() {
		Double max = (Collections.max(start.values()));
		for(Entry<Entity,Double> entry : start.entrySet()) {
			if(entry.getValue()==max) {
				return entry.getKey();
			}
		}
		return Entity.OTHER;
	}

	/**
	 * Gets the classified words.
	 *
	 * @return the classified words
	 */
	public HashMap<String, Entity> getClassifiedWords() {
		return this.classified;
	}
	
	/**
	 * Prints the classified words.
	 */
	public void printClassifiedWords() {
//		System.out.println(classified.size());
		for(Entry<String,Entity> entry : classified.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

}
