package statistics;

public class EmissionProbablity {
	private double emissionProbability;
	
	public void setEmission(int occurance, int total) {
		this.emissionProbability = occurance/total;
	}
	
	public double getEmissionProbability() {
		return this.emissionProbability;
	}

}
