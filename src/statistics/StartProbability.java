package statistics;

import java.util.ArrayList;

import entities.Entity;

public class StartProbability {
	private ArrayList<String> words;
	private ArrayList<Entity> entity;
	
	public StartProbability(ArrayList<String> w,ArrayList<Entity> e) {
		this.words=w;
		this.entity=e;
	}

}
