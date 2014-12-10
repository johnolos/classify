package patterns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import readData.ReadFileTraining;

// TODO: Auto-generated Javadoc
/**
 * The Class RegexClassificationPatterns.
 */
public class RegexClassificationPatterns {
	
	/** The Constant DATE_PATTERN_EUROPEAN. */
	//private static final String DATE_PATTERN_EUROPEAN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	
	
	private static final String DATE_EUROPEAN = "^(([0])?[1-9]|[12]\\d|3[0-1])+([ -.,/\\\\]){1,2}(((([0])?[1-9])|1\\d{1,2})|Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sept(ember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)+([ -.,/\\\\]{1,2}[12][0-9]{3})?$";
	
	/** The Constant DATE_US. */
	private static final String DATE_US = "^(((([0])?[1-9])|1\\\\d{1,2})|Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sept(ember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)+([ -.,/\\\\]){1,2}(([0])?[1-9]|[12]\\d|3[0-1])+([ -.,/\\\\]{1,2}[12][0-9]{3})?$";
	
	/** The Constant TIME_PATTERN_24. */
	private static final String TIME_PATTERN_24 = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";
	
	
	/** The Constant NAME. */
	private static final String NAME = "^[A-Z][\\p{L}([,-])? .'-]+$";
	
	
	
	
	/**
	 * Checks if is name.
	 *
	 * @param text the text
	 * @return true, if is name
	 */
	public static boolean isName(String text) {
		Pattern pattern = Pattern.compile(NAME);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
//	public static ArrayList<String> findNames(String text) {
//		// Useless.
//		ArrayList<String> matches = new ArrayList<String>();
//		Pattern pattern = Pattern.compile(NAME);
//		Matcher matcher = pattern.matcher(text);
//		int startIndex = 0;
//		while(startIndex != text.length()-1) {
//			if(!matcher.find(startIndex)) {
//				break;
//			}
//			String match = text.substring(matcher.start(), startIndex = matcher.end());
//			matches.add(match);
//		}
//		return matches;
//	}
	
	/**
 * Checks if is date.
 *
 * @param text the text
 * @return true, if is date
 */
public static boolean isDate(String text) {
		Pattern pattern_eu = Pattern.compile(DATE_EUROPEAN);
		Matcher matcher_eu = pattern_eu.matcher(text);
		Pattern pattern_us = Pattern.compile(DATE_US);
		Matcher matcher_us = pattern_us.matcher(text);
		return matcher_eu.matches() || matcher_us.matches();
	}
	
//	public static ArrayList<String> findDates(String text) {
//		ArrayList<String> dates = new ArrayList<String>();
//		
//		Pattern pattern_eu = Pattern.compile(DATE_EUROPEAN);
//		Matcher matcher_eu = pattern_eu.matcher(text);
//		int startIndex = 0;
//		while(startIndex != text.length()-1) {
//			if(!matcher_eu.find(startIndex)) {
//				break;
//			}
//			String match = text.substring(matcher_eu.start(), startIndex = matcher_eu.end());
//			dates.add(match);
//		}
//		
//		Pattern pattern_us = Pattern.compile(DATE_US);
//		Matcher matcher_us = pattern_us.matcher(text);
//		startIndex = 0;
//		while(startIndex != text.length()-1) {
//			if(!matcher_us.find(startIndex)) {
//				System.out.println("No match!");
//				break;
//			}
//			String match = text.substring(matcher_us.start(), startIndex = matcher_us.end());
//			dates.add(match);
//		}
//		return dates;
//	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		FileReader file = null;
		try {
			file = new FileReader("dates.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(file);
		String line;
		try {
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line + ": " + isDate(line));
//				ArrayList<String> matches = findDates(line);
//				for(String match : matches) {
//					System.out.println(match);
//				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
