package readTestData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import entities.Entities;

public class ReadFile {
	HashMap<String, Entities> classification;
	String filepath;
	
	public ReadFile(String path) {
		classification = new HashMap<String, Entities>();
		this.filepath = path;
		getWordsFromFile();
	}
	
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
					switch (ent) {
					case "O":
						classification.put(word, Entities.OTHER);
						break;
					case "PER":
						classification.put(word, Entities.PERSON);
						break;
					case "ORG":
						classification.put(word, Entities.ORGAINIZATION);
						break;
					case "T":
						classification.put(word, Entities.TIME);
						break;
					case "C":
						classification.put(word, Entities.COUNTRY);
						break;
					default:
						break;
					}
					printwords(word);
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Cannot find file");
		} catch(IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();
		}  catch(Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		}
	}
}
