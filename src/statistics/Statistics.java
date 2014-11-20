package statistics;

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
