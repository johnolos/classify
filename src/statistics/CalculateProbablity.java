package statistics;

public class CalculateProbablity {
	private double counter;
	private double total;
	
	public void setCounter(int i) {
		this.counter=i;
	}
	
	public void setTotal(int i) {
		this.total=i;
	}
	
	public double getCounter() {
		return this.counter;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getCalculatedProbability() {
		return (double)(this.counter/this.total);
	}
	

}
