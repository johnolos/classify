package main;

import readTestData.ReadFile;
import statistics.StartProbability;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ReadFile read = new ReadFile("C:/Users/Magnus Mogstad/Dropbox/CS273 Data and knowledge bases/test.txt");
		StartProbability prob = new StartProbability(read.getWordList(), read.getEntityList());
	}
}
