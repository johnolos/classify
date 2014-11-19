package readTestData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entities.Entities;

public class ClassifyManually {
	
	Map<String, Entities> classifiedWords;
	BufferedReader in;
	String[] characters = {",",".","(",")","?","!","'s"};
	
	public ClassifyManually(File file) {
		try {
			classifiedWords = new HashMap<String, Entities>();
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ClassifyManually(URL url) {
		try {
			classifiedWords = new HashMap<String, Entities>();
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			classifyWords(getWords());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getWords() throws IOException {
		String line;
		List<String> words = new ArrayList<String>();
		while((line = in.readLine()) != null) {
			for(String c : characters) {
				line.replace(c,"");
			}
			words.addAll(Arrays.asList(line.split(" ")));
		}
		return words;
	}
	
	public void classifyWords(List<String> words) {
		Scanner userInput = new Scanner(System.in);
		for(String word : words) {
			System.out.println("Classify " + word + ":");
			String input = userInput.nextLine();
			Entities entity = Entities.getEntity(input);
			classifiedWords.put(word, entity);
		}
	}

}
