package patterns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import readData.ReadFileTraining;

// TODO: Auto-generated Javadoc
/**
 * The Class RegexClassificationPatterns.
 */
public class RegexPatterns {
	
	/** The Constant DATE_PATTERN_EUROPEAN. */
	private static final String DATE_EUROPEAN = "^(([0])?[1-9]|[12]\\d|3[0-1])+([ -.,/\\\\]){1,2}(((([0])?[1-9])|1\\d{1,2})|Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sept(ember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)+([ -.,/\\\\]{1,2}[12][0-9]{3})?$";
	
	/** The Constant DATE_US. */
	private static final String DATE_US = "^(((([0])?[1-9])|1\\\\d{1,2})|Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sept(ember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)+([ -.,/\\\\]){1,2}(([0])?[1-9]|[12]\\d|3[0-1])+([ -.,/\\\\]{1,2}[12][0-9]{3})?$";
	
	private static final String YEAR_PATTERN = "^([1][1-9]{1}[0-9][0]+(s)?)$";
	
	private static final String YEAR_MONTH_DAY = "^(\\d{4}[.\\-\\/]\\d{2}[.\\-\\/]\\d{2})$";
	
	/** The Constant TIME_PATTERN_24. */
	private static final String TIME_PATTERN_24 = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";
	
	/** The Constant NAME. */
	private static final String NAME = "^[A-Z][\\p{L}([,-])? .'-]+$";
	
	/** Link to file containing all the countries */
	private static final String COUNTRIES_LIST = "lists/countries.txt";
	
	private static final String TIME_LIST = "lists/time.txt";
	
	private Set<String> locations; /** Common abrivations about location */
	
	private Set<String> time; /** Common words about time */
	
	public RegexPatterns(){
		locations = new HashSet<String>();
		time = new HashSet<String>();
		try {
			FileReader file = null;
			file = new FileReader(COUNTRIES_LIST);
			createDictionaryForList(this.locations, file);
			file.close();
			file = new FileReader(TIME_LIST);
			createDictionaryForList(this.time, file);
			file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createDictionaryForList(Set<String> set, FileReader file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(file);
		String line;
		while((line = bufferedReader.readLine()) != null) {
			set.add(line);
		}
		bufferedReader.close();
	}
	
	
	/**
	 * Checks if is name.
	 * @param text the text
	 * @return true, if is name
	 */
	public static boolean isName(String text) {
		Pattern pattern = Pattern.compile(NAME);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	
	public boolean isLocation(String text) {
		return locations.contains(text);
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
	public boolean isTime(String text) {
		if(time.contains(text)) {
			return true;
		}
		Pattern pattern_eu = Pattern.compile(DATE_EUROPEAN);
		Matcher matcher_eu = pattern_eu.matcher(text);
		Pattern pattern_us = Pattern.compile(DATE_US);
		Matcher matcher_us = pattern_us.matcher(text);
		Pattern pattern_time = Pattern.compile(YEAR_PATTERN);
		Matcher matcher_time = pattern_time.matcher(text);
		Pattern pattern_year = Pattern.compile(YEAR_MONTH_DAY);
		Matcher matcher_year = pattern_year.matcher(text);
		return matcher_eu.matches() 
				|| matcher_us.matches() 
				|| matcher_time.matches()
				|| matcher_year.matches();
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
		RegexPatterns pt = new RegexPatterns();
		System.out.println(pt.isLocation("Norway"));
	}
	
}
