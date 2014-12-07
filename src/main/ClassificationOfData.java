package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import entities.Entity;

public class ClassificationOfData {
	private HashMap<String,Entity> classified;
	private HashMap<String, Entity> trainingMap;
	private HashMap<Entity,Double> start;
	private Table<Entity, String, Double> transProb;
	private Table<Entity,String, Double> emissionProb;
	
	public ClassificationOfData(HashMap<String, Entity> map, HashMap<Entity,Double> start,/*Table<Entity, String, Double> trans,*/Table<Entity,String,Double> emi) {
		this.trainingMap=map;
		this.start=start;
//		this.transProb=trans;
		this.emissionProb=emi;
		this.classified = new HashMap<String, Entity>();
	}
	
	public void classify(String word) {
//		System.out.println(word);
		if(trainingMap.get(word)!=null) {
			System.out.println(trainingMap.get(word));
			classified.put(word, trainingMap.get(word));
		}
	}
	
	public HashMap<String, Entity> getClassifiedWords() {
		return this.classified;
	}
	
	public void printClassifiedWords() {
		System.out.println(classified.size());
		Iterator entries = classified.entrySet().iterator();
		while(entries.hasNext()) {
			System.out.println("HER");
			Entry thisEntry = (Entry) entries.next();
			System.out.println(thisEntry.getKey().toString());
			System.out.println(thisEntry.getValue().toString());
		}
	}

}
