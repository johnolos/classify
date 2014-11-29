package statistics;

public class TransitionProbability {
	private double transitionProb;
	
	public void setTransitionProbability(int number, int total) {
		this.transitionProb = number/total;
	}
	
	public double getTransitionProbability() {
		return this.transitionProb;
	}
	

}
