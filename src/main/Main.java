package main;

import readData.ReadFileToClassify;
import readData.ReadFileTraining;
import statistics.EmissionProbability;
import statistics.StartProbability;

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
		ReadFileTraining read = new ReadFileTraining("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test2.txt");
		System.out.println("Start");
		StartProbability prob = new StartProbability(read.getWordList(), read.getEntityList());
		System.out.println("Emission");
		EmissionProbability emi = new EmissionProbability(read.getEntityList(), read.getWordList());
		System.out.println("Classify this");
		ReadFileToClassify classificationFile = new ReadFileToClassify("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test3.txt");
		System.out.println("Classify");
		ClassificationOfData classifyData = new ClassificationOfData(read.getHashMap(), prob.getStartProbability(),emi.getTable());
		classifyData.classify("Microsoft");
		classifyData.printClassifiedWords();
		
		
	}
}
