package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import entities.Entity;

public class ClassificationOfData {
	private ArrayList<String> words;
	private HashMap<String,Entity> classified;
	private HashMap<String, Entity> trainingMap;
	private HashMap<Entity,Double> start;
	private Table<Entity, String, Double> transProb;
	private Table<Entity,String, Double> emissionProb;
	
	public ClassificationOfData(ArrayList<String> words,HashMap<String, Entity> map, HashMap<Entity,Double> start,/*Table<Entity, String, Double> trans,*/Table<Entity,String,Double> emi) {
		this.words=words;
		this.trainingMap=map;
		this.start=start;
//		this.transProb=trans;
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
//		System.out.println(word);
		String temp = word.toLowerCase();
		if(trainingMap.get(temp)!=null) {
			classified.put(word, trainingMap.get(temp));
		} else {
			classified.put(word, Entity.OTHER);
		}
	}
	
	public HashMap<String, Entity> getClassifiedWords() {
		return this.classified;
	}
	
	public void printClassifiedWords() {
		System.out.println(classified.size());
		Iterator entries = classified.entrySet().iterator();
		while(entries.hasNext()) {
//			System.out.println("HER");
			Entry thisEntry = (Entry) entries.next();
			System.out.println(thisEntry.getKey().toString());
			System.out.println(thisEntry.getValue().toString());
		}
	}

}
