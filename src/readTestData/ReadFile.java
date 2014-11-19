package readTestData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadFile {
	ArrayList<String> words;
	ArrayList<String> entity;
	String filepath;
	
	public ReadFile(String path) {
		entity = new ArrayList<String>();
		words = new ArrayList<String>();
		this.filepath = path;
		getWordsFromFile();
		printwords();
	}
	
	private void printwords() {
		for(int i=0;i<words.size();i++) {
			System.out.println(i);
			System.out.println(words.get(i));
			System.out.println(entity.get(i));
		}
	}

	private void getWordsFromFile() {
		try {
			FileReader fileReader = new FileReader(this.filepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, " ");
				while(token.hasMoreTokens()) {
					this.words.add(token.nextElement().toString());
					this.entity.add(token.nextElement().toString());
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
	
	public ArrayList<String> getWords() {
		return this.words;
	}
	
	public ArrayList<String> getEntitys() {
		return this.entity;
	}

}
