package readData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadClassifiedFile.
 */
public class ReadClassifiedFile {
	
	/** The classificated. */
	private HashMap<String, Entity> classificated;
	
	/**
	 * Instantiates a new read classified file.
	 *
	 * @param path the path
	 */
	public ReadClassifiedFile(String path) {
		this.classificated = new HashMap<String, Entity>();
		getCorrectClassification(path);
	}

	/**
	 * Gets the correct classification.
	 *
	 * @param path the path
	 * @return the correct classification
	 */
	private void getCorrectClassification(String path) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine(); 
			
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line," ");
				while(token.hasMoreTokens()) {
					String word = token.nextElement().toString();
					String entity = token.nextElement().toString();
					if(isLastWord(word)) {
						word = removeDot(word);
					}
					addClassified(word,Entity.getEntity(entity));
				}
				line = bufferedReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	 * Adds the classified.
	 *
	 * @param word the word
	 * @param entity the entity
	 */
	private void addClassified(String word,Entity entity) {
		classificated.put(word, entity);
	}
	
	/**
	 * Gets the classificated.
	 *
	 * @return the classificated
	 */
	public HashMap<String, Entity> getClassificated() {
		return this.classificated;
	}
	
}
