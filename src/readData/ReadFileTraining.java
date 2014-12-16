/*
 * 
 */
package readData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

/**
 * The Class ReadFile.
 */
public class ReadFileTraining {
	
	/** The classification. */
	HashMap<String, Entity> classification;
	
	ArrayList<String> lines;
	
	/** The words. */
	ArrayList<String> words; 
	
	/** The entit. */
	ArrayList<Entity> entit;
	
	/**
	 * Instantiates a new read file.
	 *
	 * @param path the path
	 */
	public ReadFileTraining(String path) {
		this.words = new ArrayList<String>();
		this.lines = new ArrayList<String>();
		this.entit = new ArrayList<Entity>();
		this.classification = new HashMap<String, Entity>();
		getWordsFromFile(path);
	}
	
	/**
	 * Printwords.
	 *
	 * @param word the word
	 */
	@SuppressWarnings("unused")
	private void printword(String word) {
		System.out.println(classification.size());
		System.out.println(word);
		System.out.println(classification.get(word));
	}
	
	@SuppressWarnings("unused")
	private void printLines() {
		System.out.println("lines");
		for(int i=0; i<this.lines.size();i++) {
			System.out.println(i+ " - "+this.lines.get(i));
		}
	}

	/**
	 * Gets the words from file.
	 *
	 * @param path the path
	 * @return the words from file
	 */
	private void getWordsFromFile(String path) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, " ");
				while(token.hasMoreTokens()) {
					String word = token.nextElement().toString();
					String ent = token.nextElement().toString();
					addWord(word);
					addEntity(ent);					
					if(isLastWord(word)) {
						word = removeDot(word);
					}
//					System.out.println(word);
					addClassification(word,Entity.getEntity(ent));
//					printwords(word);
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Cannot find file");
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("IO exception");
		}  catch(Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong, are you sure every word has an entity that is supported?");
		}
	}
	
	@SuppressWarnings("unused")
	private void addLine(String line) {
		this.lines.add(line);
	}

	/**
	 * Adds the classification.
	 *
	 * @param word the word
	 * @param entity the entity
	 */
	private void addClassification(String word, Entity entity) {
		this.classification.put(word.toLowerCase(), entity);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param entity the entity
	 */
	private void addEntity(String entity) {
		this.entit.add(Entity.getEntity(entity));	
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
	 * Gets the hash map.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Entity> getHashMap() {
		return this.classification;
	}
	
	/**
	 * Gets the entity list.
	 *
	 * @return the entity list
	 */
	public ArrayList<Entity> getEntityList() {
		return this.entit;
	}
	
	/**
	 * Gets the word list.
	 *
	 * @return the word list
	 */
	public ArrayList<String> getWordList() {
		return this.words;
	}
	
	public ArrayList<String> getLines() {
		return this.lines;
	}
}
