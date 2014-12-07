package patterns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import readTestData.ReadFile;

public class RegexClassificationPatterns {
	
	private static final String DATE_PATTERN_EUROPEAN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	private static final String TIME_PATTERN_24 = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";
	
	
	private static final String NAME = "^[a-zA-Z0-9]*[a-zA-Z]+[a-zA-Z0-9]*$";
	
	
	
	
	public static boolean isName(String text) {
		Pattern pattern = Pattern.compile(NAME);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		FileReader file = null;
		try {
			file = new FileReader("names.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(file);
		String line;
		try {
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line + ": " + isName(line));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
