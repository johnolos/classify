package main;

import readTestData.ReadFile;
import statistics.EmissionProbability;
import statistics.StartProbability;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ReadFile read = new ReadFile("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test2.txt");
		StartProbability prob = new StartProbability(read.getWordList(), read.getEntityList());
		EmissionProbability emi = new EmissionProbability(read.getEntityList(), read.getWordList());
	}
}
