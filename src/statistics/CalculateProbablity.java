package statistics;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculateProbablity.
 */
public class CalculateProbablity {
	
	/** The counter. */
	private double counter;
	
	/** The total. */
	private double total;
	
	/**
	 * Sets the counter.
	 *
	 * @param i the new counter
	 */
	public void setCounter(int i) {
		this.counter=i;
	}
	
	/**
	 * Sets the total.
	 *
	 * @param i the new total
	 */
	public void setTotal(int i) {
		this.total=i;
	}
	
	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public double getCounter() {
		return this.counter;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return this.total;
	}
	
	/**
	 * Gets the calculated probability.
	 *
	 * @return the calculated probability
	 */
	public double getCalculatedProbability() {
		return (double)(this.counter/this.total);
	}
	

}
