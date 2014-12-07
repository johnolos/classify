package readData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadFileToClassify {
	private ArrayList<String> words;
	private ArrayList<String> wordsWithoutDot;
	
	public ReadFileToClassify(String path) {
		words = new ArrayList<String>();
		wordsWithoutDot = new ArrayList<String>();
		read(path);
		printWords();
	}
	
	private void printWords() {
		for(int i=0; i<this.words.size();i++) {
			System.out.println(words.get(i));
		}
		
	}
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
	
	private boolean isLastWord(String word) {
		String temp = word.substring(word.length()-1, word.length());
		if(temp.equals(".")) {
			return true;
		}
		return false;
	}
	
	private String removeDot(String word) {
		return word.substring(0, word.length()-1).trim();
	}

	private void addWord(String word) {
		this.words.add(word);		
	}
	
	private void addWordWithoutDot(String word) {
		this.wordsWithoutDot.add(word);
	}
	
	public ArrayList<String> getWordsWithoutDot() {
		return this.wordsWithoutDot;
	}

	public ArrayList<String> getWords() {
		return this.words;
	}

}
