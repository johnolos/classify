package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import entities.Entity;

public class ClassificationOfData {
	private ArrayList<String> words;
	private HashMap<String,Entity> classified;
	private HashMap<String, Entity> trainingMap;
	private HashMap<Entity,Double> start;
	private Table<Entity, Entity, Float> transProb;
	private Table<Entity,String, Double> emissionProb;
	private boolean newSentence=true;
	private Entity lastEntity;
	
	public ClassificationOfData(ArrayList<String> words,HashMap<String, Entity> map, HashMap<Entity,Double> start,Table<Entity, Entity, Float> trans,Table<Entity,String,Double> emi) {
		this.words=words;
		this.trainingMap=map;
		this.start=start;
		this.transProb=trans;
		this.emissionProb=emi;
		this.classified = new HashMap<String, Entity>();
		runClassify();
	}
	
	public void runClassify() {
		for(int i=0;i<this.words.size();i++) {
			classify(this.words.get(i));
		}
	}
	
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

	private Entity findMaxStart() {
		Double max = (Collections.max(start.values()));
		for(Entry<Entity,Double> entry : start.entrySet()) {
			if(entry.getValue()==max) {
				return entry.getKey();
			}
		}
		return Entity.OTHER;
	}

	public HashMap<String, Entity> getClassifiedWords() {
		return this.classified;
	}
	
	public void printClassifiedWords() {
//		System.out.println(classified.size());
		for(Entry<String,Entity> entry : classified.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

}
