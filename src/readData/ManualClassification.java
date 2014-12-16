package readData;

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

import readTestData.Classification;
import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassifyManually.
 */
public class ManualClassification {
	
	/** The classified words. */
	ArrayList<Classification> classifiedWords;
	
	/** The in. */
	BufferedReader in;
	
	/**
	 * Instantiates a new classify manually.
	 *
	 * @param file the file
	 */
	public ManualClassification(File file) {
		try {
			classifiedWords = new ArrayList<Classification>();
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
	
	/**
	 * Instantiates a new classify manually.
	 *
	 * @param url the url
	 */
	public ManualClassification(URL url) {
		try {
			classifiedWords = new ArrayList<Classification>();
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			Scanner user = new Scanner(System.in);
			classifyWords(getWords(true), user);
			writeClassifiedWord(classifiedWords, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the words.
	 *
	 * @param html the html
	 * @return the words
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> getWords(boolean html) throws IOException {
		String line;
		List<String> words = new ArrayList<String>();
		while((line = in.readLine()) != null) {
			if(html)
				line = Jsoup.parse(line).text();
			words.addAll(Arrays.asList(line.split(" ")));
		}
		return words;
	}
	
	/**
	 * Classify words.
	 *
	 * @param words the words
	 * @param user the user
	 */
	public void classifyWords(List<String> words, Scanner user) {
		int wordSize = words.size();
		System.out.println("Number of words: " + wordSize);
		int count = 0;
		for(String word : words) {
			int remainingWords = wordSize - count;
			System.out.println("Words remaining: " + remainingWords);
			if(word.equals(""))
				continue;
//			String firstLetter = word.substring(0, 1);
			String input = "";
//			if(firstLetter.matches("[a-z]")) {
//				System.out.println("OTHER");
//			} else {
			System.out.println("Classify " + word + ":");
			input = user.nextLine();
			if(input.equals("exit"))
				break;
			if(input.equals(""))
				input = "O";
//			}
			count++;
			Entity entity = Entity.getEntity(input);
			classifiedWords.add(new Classification(word, entity));
		}
	}

	/**
	 * Write classified word.
	 *
	 * @param classifiedWords2 the map
	 * @param user the user
	 * @throws FileNotFoundException the file not found exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static void writeClassifiedWord(ArrayList<Classification> arrayOfClassification, Scanner user) 
			throws FileNotFoundException, UnsupportedEncodingException {
		System.out.println("Filename: ");
		String filename = user.nextLine();
		while(filename == "" || filename == " ") {
			filename = user.nextLine();
		}
		if (filename.equals("exit"))
			return;
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for(Classification classifiedWord : arrayOfClassification) {
			writer.println(classifiedWord.word + " " + classifiedWord.classification);
		}
		writer.close();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		File file = new File("testDocs/testArticle4.txt");
		ManualClassification classify = new ManualClassification(file);
	}
}
