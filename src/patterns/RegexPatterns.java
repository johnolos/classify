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
	
	/** Year pattern*/
	private static final String YEAR_PATTERN = "^(\\d{4})+([-]\\d{2})?$";
	
	/** YYYY[.\-]MM[.\-]DD Pattern*/
	private static final String YEAR_MONTH_DAY = "^(\\d{4}[.\\-\\/]\\d{2}[.\\-\\/]\\d{2})$";
	
	/** The Constant TIME_PATTERN_24. */
	private static final String TIME_PATTERN_24 = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";
	
	/** The Constant NAME. */
	private static final String NAME = "^[A-Z][\\p{L}([,-])? .'-]+$";
	
	/** Link to file containing all the countries */
	private static final String COUNTRIES_LIST = "lists/countries.txt";
	
	private static final String TIME_LIST = "lists/time.txt";
	
	private static final String NAME_LIST = "lists/names.txt";
	
	/** International locations */
	private Set<String> locations;
	
	/** Common words about time */
	private Set<String> time;
	
	/** Common first names of male and female as
	well as common last names*/
	private Set<String> names;
	
	/**
	 * Class RegexPatterns
	 * Class to check if strings match a certain pattern.
	 */
	public RegexPatterns(){
		locations = new HashSet<String>();
		time = new HashSet<String>();
		names = new HashSet<String>();
		try {
			FileReader file = null;
			file = new FileReader(COUNTRIES_LIST);
			createDictionaryForList(this.locations, file);
			file.close();
			file = new FileReader(TIME_LIST);
			createDictionaryForList(this.time, file);
			file.close();
			file = new FileReader(NAME_LIST);
			createDictionaryForList(this.names, file);
			file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Help function to create dictionaries for our .txt files to help classify when
	 * algorithm is unsure of the classification.
	 * @param set
	 * @param file
	 * @throws IOException
	 */
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
	public boolean isName(String text) {
		return names.contains(text);
	}
	
	/**
	 * Check if a text string matches a entry in our location knowledge.
	 * @param text
	 * @return
	 */
	public boolean isLocation(String text) {
		return locations.contains(text);
	}

	
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
		return text.matches(YEAR_PATTERN) || text.matches(DATE_EUROPEAN)
				|| text.matches(DATE_US) || text.matches(YEAR_MONTH_DAY)
				|| text.matches(TIME_PATTERN_24);
	}
		
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		RegexPatterns pt = new RegexPatterns();
		System.out.println(pt.isLocation("Norway"));
		System.out.println(pt.isTime("2002"));
		System.out.println(pt.isTime("2002-02-01"));
		System.out.println(pt.isTime("01-02-2014"));
		System.out.println(pt.isTime("01.02.2014"));
	}
	
}
