package readTestData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entity;

public class ReadClassification {
	
	// Unused
	
	
	String filepath;
	FileReader fileReader;
	BufferedReader bufferedReader;
	
	public ReadClassification(String path) {
		this.filepath = path;
	}
	
	public void openFile() throws FileNotFoundException {
		fileReader = new FileReader(this.filepath);
		bufferedReader = new BufferedReader(this.fileReader);
	}
	
	public void closeFile() throws IOException {
		this.bufferedReader.close();
		this.fileReader.close();
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
//					this.entit.add(Entity.getEntity(ent));
//					classification.put(word, Entity.getEntity(ent));
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
//	public HashMap<String, Entity> getHashMap() {
//		return this.classification;
//	}
//	
//	public ArrayList<Entity> getEntityList() {
//		return this.entit;
//	}
//	
//	public ArrayList<String> getWordList() {
//		return this.words;
//	}
}
