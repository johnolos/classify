package main;

import readData.ReadClassifiedFile;
import readData.ReadFileToClassify;
import readData.ReadFileTraining;
import statistics.EmissionProbability;
import statistics.StartProbability;
import statistics.Statistics;
import statistics.TransmissionProbability;

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
		ReadFileTraining read = new ReadFileTraining("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test2.txt");
		System.out.println("Start");
		StartProbability prob = new StartProbability(read.getWordList(), read.getEntityList());
		System.out.println("Emission");
		EmissionProbability emi = new EmissionProbability(read.getEntityList(), read.getWordList());
		System.out.println("Classify this");
		ReadFileToClassify classificationFile = new ReadFileToClassify("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test4.txt");
		System.out.println("Transmission");
		TransmissionProbability trans = new TransmissionProbability("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test2.txt");
		trans.runTransmissionProbabilistic();
		System.out.println("Classify");
		ClassificationOfData classifyData = new ClassificationOfData(classificationFile.getWords(),read.getHashMap(),prob.getStartProbability(),trans.getTable(),emi.getTable());
		classifyData.printClassifiedWords();
		
		ReadClassifiedFile classified = new ReadClassifiedFile("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test3.txt");
		Statistics statistics = new Statistics(classifyData.getClassifiedWords(), classified.getClassificated());
		System.out.println("Correct " + statistics.getCorrect());
		System.out.println("Error " + statistics.getError());
		System.out.println("Total " + statistics.getTotal());
		System.out.println("CRate" + statistics.getCorrectRate());
		System.out.println("ERate" + statistics.getErrorRate());
	}
}
