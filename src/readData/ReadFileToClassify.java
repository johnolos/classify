package readData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadFileToClassify.
 */
public class ReadFileToClassify {
	
	/** The words. */
	private ArrayList<String> words;
	
	/** The words without dot. */
	private ArrayList<String> wordsWithoutDot;
	
	/**
	 * Instantiates a new read file to classify.
	 *
	 * @param path the path
	 */
	public ReadFileToClassify(String path) {
		words = new ArrayList<String>();
		wordsWithoutDot = new ArrayList<String>();
		read(path);
		printWords();
	}
	
	/**
	 * Prints the words.
	 */
	private void printWords() {
		for(int i=0; i<this.words.size();i++) {
			System.out.println(words.get(i));
		}
		
	}
	
	/**
	 * Read.
	 *
	 * @param path the path
	 */
	private void read(String path) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while(line!=null) {
				StringTokenizer token = new StringTokenizer(line, " ");
				while(token.hasMoreElements()) {
					String word = token.nextElement().toString();
					addWord(word);
					if(isLastWord(word)) {
						word = removeDot(word);
						addWordWithoutDot(word);
					}					
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File does not exist");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		}
	}
	
	/**
	 * Checks if is last word.
	 *
	 * @param word the word
	 * @return true, if is last word
	 */
	private boolean isLastWord(String word) {
		String temp = word.substring(word.length()-1, word.length());
		if(temp.equals(".")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the dot.
	 *
	 * @param word the word
	 * @return the string
	 */
	private String removeDot(String word) {
		return word.substring(0, word.length()-1).trim();
	}

	/**
	 * Adds the word.
	 *
	 * @param word the word
	 */
	private void addWord(String word) {
		this.words.add(word);		
	}
	
	/**
	 * Adds the word without dot.
	 *
	 * @param word the word
	 */
	private void addWordWithoutDot(String word) {
		this.wordsWithoutDot.add(word);
	}
	
	/**
	 * Gets the words without dot.
	 *
	 * @return the words without dot
	 */
	public ArrayList<String> getWordsWithoutDot() {
		return this.wordsWithoutDot;
	}

	/**
	 * Gets the words.
	 *
	 * @return the words
	 */
	public ArrayList<String> getWords() {
		return this.words;
	}

}
