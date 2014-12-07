/*
 * 
 */
package readTestData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadFile.
 */
public class ReadFile {
	
	/** The classification. */
	HashMap<String, Entity> classification;
	
	/** The words. */
	ArrayList<String> words; 
	
	/** The entit. */
	ArrayList<Entity> entit;
	
	/** The filepath. */
	String filepath;
	
	/**
	 * Instantiates a new read file.
	 *
	 * @param path the path
	 */
	public ReadFile(String path) {
		this.words = new ArrayList<String>();
		this.entit = new ArrayList<Entity>();
		this.classification = new HashMap<String, Entity>();
		this.filepath = path;
		getWordsFromFile();
	}
	
	/**
	 * Printwords.
	 *
	 * @param word the word
	 */
	@SuppressWarnings("unused")
	private void printwords(String word) {
		System.out.println(classification.size());
		System.out.println(word);
		System.out.println(classification.get(word));
	}

	/**
	 * Gets the words from file.
	 *
	 * @return the words from file
	 */
	private void getWordsFromFile() {
		try {
			FileReader fileReader = new FileReader(this.filepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, " ");
				while(token.hasMoreTokens()) {
					String word = token.nextElement().toString();
					String ent = token.nextElement().toString();
					this.words.add(word);
					this.entit.add(Entity.getEntity(ent));
					if(isLastWord(word)) {
						word = removeDot(word);
					}
					classification.put(word, Entity.getEntity(ent));
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
			System.out.println("Something went wrong, are you shure every word has an entity supportet?");
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
}
