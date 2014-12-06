package statistics;

public class CalculateProbablity {
	private int counter;
	private int total;
	private double prob;
	
	public void setCounter(int i) {
		this.counter=i;
	}
	
	public void setTotal(int i) {
		this.total=i;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void calculateProbability() {
		this.prob=counter/total;
	}
	
	public double getProbability() {
		return this.prob;
	}

}
