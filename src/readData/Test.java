package readData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import patterns.RegexPatterns;
import entities.Entity;

public class Test {

	
	public Test(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		RegexPatterns pattern = new RegexPatterns();
		String line;
		while((line = in.readLine()) != null) {
			Entity classification = null;
			if(RegexPatterns.isTime(line)) {
				classification = Entity.TIME;
			}
			if(pattern.isCountry(line)) {
				classification = Entity.LOCATION;
			}
			System.out.println(line + ": " + classification);
		}
	}
	
	static public void main(String[] args) {
		File file = new File("testDocs/testRegexExpressions.txt");
		try {
			Test test = new Test(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
