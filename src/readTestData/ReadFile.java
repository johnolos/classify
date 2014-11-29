package readTestData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

public class ReadFile {
	HashMap<String, Entity> classification;
//	ArrayList<String> words, entit;
	String filepath;
	
	public ReadFile(String path) {
//		this.words = new ArrayList<String>();
//		this.entit = new ArrayList<String>();
		this.classification = new HashMap<String, Entity>();
		this.filepath = path;
		getWordsFromFile();
	}
	
	@SuppressWarnings("unused")
	private void printwords(String word) {
		System.out.println(classification.size());
		System.out.println(word);
		System.out.println(classification.get(word));
	}

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
//					this.words.add(word);
//					this.entit.add(ent);
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
			System.out.println("Something went wrong");
		}
	}
	public HashMap<String, Entity> getHashMap() {
		return this.classification;
	}
}
