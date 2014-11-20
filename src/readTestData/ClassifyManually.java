package readTestData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;

import entities.Entities;

public class ClassifyManually {
	
	Map<String, Entities> classifiedWords;
	BufferedReader in;
	
	public ClassifyManually(File file) {
		try {
			classifiedWords = new HashMap<String, Entities>();
			in = new BufferedReader(new FileReader(file));
			Scanner user = new Scanner(System.in);
			classifyWords(getWords(false), user);
			writeClassifiedWord(classifiedWords, user);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ClassifyManually(URL url) {
		try {
			classifiedWords = new HashMap<String, Entities>();
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			Scanner user = new Scanner(System.in);
			classifyWords(getWords(true), user);
			writeClassifiedWord(classifiedWords, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getWords(boolean html) throws IOException {
		String line;
		List<String> words = new ArrayList<String>();
		while((line = in.readLine()) != null) {
			if(html)
				line = Jsoup.parse(line).text();
			line = line.replaceAll("[^a-zA-Z]+"," ");
			words.addAll(Arrays.asList(line.split(" ")));
		}
		return words;
	}
	
	public void classifyWords(List<String> words, Scanner user) {
		for(String word : words) {
			if(word.equals(""))
				continue;
			System.out.println("Classify " + word + ":");
			String input = user.nextLine();
			if(input.equals("exit"))
				break;
			Entities entity = Entities.getEntity(input);
			classifiedWords.put(word, entity);
		}
	}

	public static void writeClassifiedWord(Map<String, Entities> map, Scanner user) 
			throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("Filename: ");
		String filename = user.nextLine();
		if (filename.equals("exit"))
			return;
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for(Map.Entry<String, Entities> entry : map.entrySet()) {
			writer.println(entry.getKey() + " " + entry.getValue());
		}
		writer.close();
	}
	
	public static void main(String[] args) {
		try {
			URL u = new URL("http://en.wikipedia.org/wiki/Point_in_polygon");
			ClassifyManually classify = new ClassifyManually(u);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
