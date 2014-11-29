package statistics;

public class StartProbability {
	private double startProb;
	
	public void setStartProb(int tag, int total) {
		this.startProb = tag/total;
	}
	
	public double getStartProbability() {
		return this.startProb;
	}
	
}
