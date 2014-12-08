package readData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

public class ReadClassifiedFile {
	private HashMap<String, Entity> classificated;
	
	public ReadClassifiedFile(String path) {
		this.classificated = new HashMap<String, Entity>();
		getCorrectClassification(path);
	}

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
	
	private String removeDot(String word) {
		return word.substring(0, word.length()-1).trim();
	}

	private boolean isLastWord(String word) {
		String temp = word.substring(word.length()-1, word.length());
		if(temp.equals(".")) {
			return true;
		}
		return false;
	}

	private void addClassified(String word,Entity entity) {
		classificated.put(word, entity);
	}
	
	public HashMap<String, Entity> getClassificated() {
		return this.classificated;
	}
	
}
