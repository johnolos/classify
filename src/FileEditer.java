import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileEditer {
	
	
	public static void main(String[] args) {
		try {
			FileReader file = new FileReader("worldcitiespop.txt");
			BufferedReader reader = new BufferedReader(file);
			String line;
			while((line = reader.readLine()) == null) {
				String[] words = line.split(",");
				String country = "";
				for(int i = 1; i <= words.length; i++) {
					country+=words[i];
				}
				System.out.println(country);
				System.out.println(words[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
