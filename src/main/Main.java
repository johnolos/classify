package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import readData.ReadClassifiedFile;
import readData.ReadFileToClassify;
import readData.ReadFileTraining;
import statistics.EmissionProbability;
import statistics.StartProbability;
import statistics.Statistics;
import statistics.TransmissionProbability;
import viterbi.Viterbi;
import viterbi.Viterbi.ObservationException;

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
		ReadFileTraining read = new ReadFileTraining("testDocs/test2.txt");
		System.out.println("Start");
		StartProbability startProb = new StartProbability(read.getWordList(), read.getEntityList());
		System.out.println("Emission");
		EmissionProbability emi = new EmissionProbability(read.getEntityList(), read.getWordList());
		System.out.println("Transmission");
		TransmissionProbability trans = new TransmissionProbability("testDocs/test2.txt");
		trans.runTransmissionProbabilistic();
		System.out.println("Classify this");
		ReadFileToClassify classificationFile = new ReadFileToClassify("testDocs/test4.txt");
		System.out.println("Classify");
//		ClassificationOfData classifyData = new ClassificationOfData(classificationFile.getWords(),read.getHashMap(),prob.getStartProbability(),trans.getTable(),emi.getTable());
//		classifyData.printClassifiedWords();
		Viterbi viterbi = new Viterbi(null, trans.getTable(), emi.getTable(), startProb.getStartProbability());
		ArrayList<ArrayList<String>> linesToClassify = classificationFile.getWordLines();
		List<Entity> classifiedStates;
		for (ArrayList<String> line : linesToClassify) {
			if (line == null) continue;
			viterbi.setObservations(line);
			try {
				viterbi.run();
				classifiedStates = viterbi.getStates();
				for(int i = 0; i < line.size(); i++) {
					System.out.println("Word: " + line.get(i) + " , Entity: " + classifiedStates.get(i));
				}
			} catch (ObservationException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		ReadClassifiedFile classified = new ReadClassifiedFile("testDocs/test3.txt");
//		Statistics statistics = new Statistics(classifyData.getClassifiedWords(), classified.getClassificated());
//		System.out.println("Correct " + statistics.getCorrect());
//		System.out.println("Error " + statistics.getError());
//		System.out.println("Total " + statistics.getTotal());
//		System.out.println("CRate" + statistics.getCorrectRate());
//		System.out.println("ERate" + statistics.getErrorRate());
	}
}
