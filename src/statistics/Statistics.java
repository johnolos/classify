package statistics;

import java.util.HashMap;

import entities.Entity;

public class Statistics {
	//THIS CLASS NEEDS FIXING BUT THIS IS A FRAME
	int total;
	int correct;
	int error;
	double correctRate;
	double errorRate;
	
	public Statistics() {
		this.total = 0; //FIX
		this.correct = 0; //FIX
		this.error = 0; //FIX
	}
	
	//This is maybe not the best way but we will se if it will be used or not
	public void addCorrectOrError(HashMap<String, Entity> one, HashMap<String, Entity> other,String string) {
		if(one.get(string).equals(other.get(string))) {
			this.correct++;
			this.total++;
		} else {
			this.total++;
			this.error++;
		}
	}
	
	public void calcCorrectRate() {
		this.correctRate = this.correct/this.total;
	}
	
	public double getCorrectRate() {
		return this.correctRate;
	}
	
	public void calcErrorRate() {
		this.errorRate = this.error/this.total;
	}
	
	public double getErrorRate() {
		return this.errorRate;
	}
}
