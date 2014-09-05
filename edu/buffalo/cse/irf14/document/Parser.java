/**
 * 
 */
package edu.buffalo.cse.irf14.document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nikhillo
 * Class that parses a given file into a Document
 */
public class Parser {
	public static final String[] MONTHS = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
	public static final int MIN_LENGTH_OF_MONTH_ABBR = 3;	//the minimum length of the abbreviations of the month
	
	//two REGEX patterns for <AUTHOR>    By    <AUTHOR> 
	public static Pattern patternFront = Pattern.compile("^<AUTHOR>\\u0020*By\\u0020*", Pattern.CASE_INSENSITIVE);
	public static Pattern patternBack = Pattern.compile("</AUTHOR>$");
	
	
	
 	/**
	 * Static method to parse the given file into the Document object
	 * @param filename : The fully qualified filename to be parsed
	 * @return The parsed and fully loaded Document object
	 * @throws ParserException In case any error occurs during parsing
	 */
	public static Document parse(String filename) throws ParserException {
		// TODO Auto-generated method stub
	
		if(filename == null){
			throw new ParserException();
		} else {
			Document document = new Document();
			ArrayList<String> linesFromDocument = storeDocumentIntoArray(filename);
		
			String fileId = getFileId(filename);
			if(fileId != null){
				String category = getCategory(filename);
				String title = getTitle(linesFromDocument);
				String place = getPlace(linesFromDocument);
				String date = getDate(linesFromDocument);
				String content = getContent(linesFromDocument);
				String authorName = getAuthorName(linesFromDocument);
				String authorOrg = getAuthorOrg(linesFromDocument);
				document.setField(FieldNames.FILEID, fileId);
				document.setField(FieldNames.CATEGORY, category);
				document.setField(FieldNames.TITLE, title);
				document.setField(FieldNames.PLACE, place);
				document.setField(FieldNames.NEWSDATE, date);
				document.setField(FieldNames.CONTENT, content);
				
				if(authorName != null) {
					document.setField(FieldNames.AUTHOR, authorName);
				}
				if(authorOrg != null) {
					document.setField(FieldNames.AUTHORORG, authorOrg);
				}
				
				//System.out.println(content);
				return document;
			}
			else{
				return null;
			}
		}
	}
	
	/** Task: finds out Author's organization
	 * @return author's organization
	 */
	
	private static String getAuthorOrg(ArrayList<String> linesFromDocument){
		
		String authorData = getAuthorData(linesFromDocument);
		
		if(authorData != null){
			StringTokenizer authorTok = new StringTokenizer(authorData, ",");
			if(authorTok.countTokens() > 1){
				authorTok.nextToken(); //don't care
				return authorTok.nextToken().trim();
			}
		}
		return null;
	}
	
	/** Task: finds out Author's name
	 * @return author's name
	 */
	
	private static String getAuthorName(ArrayList<String> linesFromDocument){
		
		String authorData = getAuthorData(linesFromDocument);
		
		if(authorData != null){
			StringTokenizer authorTok = new StringTokenizer(authorData, ",");
			return authorTok.nextToken();
		}
		return null;
	}
	
	/** Task: finds out Author's name and Organization
	 * @return author's name and organization
	 */
	
	private static String getAuthorData(ArrayList<String> linesFromDocument){
		
		String line = linesFromDocument.get(1);
		if(line.contains("<AUTHOR>")){
			Matcher matcher = patternFront.matcher(line);
			String temp = matcher.replaceAll("");
			
			matcher = patternBack.matcher(temp);
			String authorInfo = matcher.replaceAll("");
			
			return authorInfo;
		}
		return null;
	}

	/** Task: finds the fileId from file path given
	 * @return the fileId from the filename given
	 */
	private static String getFileId(String filename){
		StringTokenizer tokenizer = new StringTokenizer(filename, "/");
		String fileId = "";
		while(tokenizer.hasMoreElements()){
			fileId = tokenizer.nextToken();
		}
		if(fileId.compareTo(".DS_Store") == 0){
			fileId = null;
		}
		return fileId;
	}
	/** Task: will get the parent directory that holds the file
	 * @param filename
	 * @return the string for the category name
	 */
	private static String getCategory(String filename){
		StringTokenizer tokenizer = new StringTokenizer(filename, "/");
		String category = "";
		int count = tokenizer.countTokens();
		for(int i = 0; i < count-1; i++){
			category = tokenizer.nextToken(); 
		}
		return category;
	}
	/** Task: Gets the Title which we assume is the first element of the array.
	 * @param linesFromDocument
	 * @return returns the title from the linesFromDocument argument
	 */
	private static String getTitle(ArrayList<String> linesFromDocument){
		return linesFromDocument.get(0);
	}
	/** Task: This will get the location from the line
	 * @param linesFromDocument
	 * @return the place where the news is reported
	 */
	private static String getPlace(ArrayList<String> linesFromDocument){
		String place = "";
		StringTokenizer placeAndDate = getPlaceAndDate(linesFromDocument);
		StringTokenizer tokenizer = new StringTokenizer(placeAndDate.nextToken(), ",");
		int count = tokenizer.countTokens();
		for(int i = 0; i < count - 2; i++){
			place += tokenizer.nextToken() + ",";		
		}
		
		place += tokenizer.nextToken();
		
		return place;
	}
	
	/**
	 * Task: gets you the date from the document
	 * @param linesFromDocument
	 * @return the date in string format
	 */
	private static String getDate(ArrayList<String> linesFromDocument){
		String date = "";
		StringTokenizer placeAndDate = getPlaceAndDate(linesFromDocument);
		StringTokenizer tokenizer = new StringTokenizer(placeAndDate.nextToken(), ",");
		int count = tokenizer.countTokens();
		for(int i = 0; i < count - 1; i++){
			 tokenizer.nextToken();		
		}
		date = tokenizer.nextToken();
		if(date.length() >= 4){
			String month = date.substring(0,4).trim();
			for(int i = 0; i < MONTHS.length; i++){
				if(month.toLowerCase().compareTo(MONTHS[i]) == 0){
					return date.trim();
				}
			}
			return null;
		}
		else{
			return null;
		}
		
	
	}
	/** Task: This will just get you the place and date from the arraylist
	 * @param linesFromDocument
	 * @return string tokenizer with place and date in it
	 */
	private static StringTokenizer getPlaceAndDate(ArrayList<String> linesFromDocument){
		String line = linesFromDocument.get(1);	
		//Filters out the Author and random digits
		if(line.contains("<AUTHOR>")){
			line = linesFromDocument.get(2);
		}
		else if(Character.isDigit(line.charAt(0))){
			line = linesFromDocument.get(2);
		}
		StringTokenizer placeAndDate = new StringTokenizer(line, "-");
		return placeAndDate;
	}
	/** Task gets you the content
	 * @param linesFromDocument
	 * @return the entire content in the string 
	 */
	private static String getContent(ArrayList<String> linesFromDocument){
		String content = "";
		int firstLineOfContent = 0;
		//I chose 1 for i because 0006224 had a - in the first line so that ruined everything. 
		for(int i = 1; i < linesFromDocument.size(); i++){
			if(linesFromDocument.get(i).contains("-")){
				firstLineOfContent = i;
				break;
			}
		}
		StringTokenizer tokenizer = new StringTokenizer(linesFromDocument.get(firstLineOfContent), "-");
		for(int i = 0; i < tokenizer.countTokens(); i++){
			tokenizer.nextToken();
		}
		
		try{
			content += tokenizer.nextToken();
			for(int i = firstLineOfContent + 1; i < linesFromDocument.size(); i++){
				content += " " + linesFromDocument.get(i);
			}
		}
		
		catch(NoSuchElementException e){
			content = null;
		}

		return content;
	}

	/** Task: stores each line from the document as an element of the array list 
	 * @param filename
	 * @return ArrayList with each line from the document as an element of the arraylist.
	 * @throws ParserException 
	 */
	private static ArrayList<String> storeDocumentIntoArray(String filename) throws ParserException{
		ArrayList<String> linesFromDocument = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			try {
				while((line = bufferedReader.readLine()) != null){
					if(line.trim().length() != 0){
						linesFromDocument.add(line.trim());
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.toString());
			//e.printStackTrace();
			throw new ParserException();
		}
		
		return linesFromDocument;
	}
	
}
