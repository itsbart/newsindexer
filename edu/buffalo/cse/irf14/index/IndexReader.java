/**
 * 
 */
package edu.buffalo.cse.irf14.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import edu.buffalo.cse.irf14.index.IndexWriter.IndexNames;

/**
 * @author nikhillo Class that emulates reading data back from a written index
 */
public class IndexReader {

	private String _filePath;
	private IndexType _type;
	private IndexNames _name;

	private TreeMap<String, LinkedList<Integer>> _index;

	/**
	 * Default constructor
	 * 
	 * @param indexDir
	 *            : The root directory from which the index is to be read. This
	 *            will be exactly the same directory as passed on IndexWriter.
	 *            In case you make subdirectories etc., you will have to handle
	 *            it accordingly.
	 * @param type
	 *            The {@link IndexType} to read from
	 */
	public IndexReader(String indexDir, IndexType type) {

		// save index info
		_filePath = indexDir;
		_type = type;
		_index = new TreeMap<String, LinkedList<Integer>>();
		
		
		switch (type) {

		case AUTHOR:
			_name = IndexNames.AUTHOR_INDEX;
			break;

		case CATEGORY:
			_name = IndexNames.CATEGORY_INDEX;
			break;

		case PLACE:
			_name = IndexNames.PLACE_INDEX;
			break;

		case TERM:
			_name = IndexNames.TERM_INDEX;
			break;

		default:
			_name = null;
			break;

		}

		// read in index
		this.uploadIndex();
	}

	/**
	 * Get total number of terms from the "key" dictionary associated with this
	 * index. A postings list is always created against the "key" dictionary
	 * 
	 * @return The total number of terms
	 */
	public int getTotalKeyTerms() {
		// TODO : YOU MUST IMPLEMENT THIS
		return -1;
	}

	/**
	 * Get total number of terms from the "value" dictionary associated with
	 * this index. A postings list is always created with the "value" dictionary
	 * 
	 * @return The total number of terms
	 */
	public int getTotalValueTerms() {
		// TODO: YOU MUST IMPLEMENT THIS
		return -1;
	}

	/**
	 * Method to get the postings for a given term. You can assume that the raw
	 * string that is used to query would be passed through the same Analyzer as
	 * the original field would have been.
	 * 
	 * @param term
	 *            : The "analyzed" term to get postings for
	 * @return A Map containing the corresponding fileid as the key and the
	 *         number of occurrences as values if the given term was found, null
	 *         otherwise.
	 */
	public Map<String, Integer> getPostings(String term) {
		// TODO:YOU MUST IMPLEMENT THIS
		return null;
	}

	/**
	 * Method to get the top k terms from the index in terms of the total number
	 * of occurrences.
	 * 
	 * @param k
	 *            : The number of terms to fetch
	 * @return : An ordered list of results. Must be <=k fr valid k values null
	 *         for invalid k values
	 */
	public List<String> getTopK(int k) {
		// TODO YOU MUST IMPLEMENT THIS
		return null;
	}

	/**
	 * Method to implement a simple boolean AND query on the given index
	 * 
	 * @param terms
	 *            The ordered set of terms to AND, similar to getPostings() the
	 *            terms would be passed through the necessary Analyzer.
	 * @return A Map (if all terms are found) containing FileId as the key and
	 *         number of occurrences as the value, the number of occurrences
	 *         would be the sum of occurrences for each participating term.
	 *         return null if the given term list returns no results BONUS ONLY
	 */
	public Map<String, Integer> query(String... terms) {
		// TODO : BONUS ONLY
		return null;
	}

	
	public void process(String line){
		if(line != null){
		
			String[] splitted = line.split(" ");
			
			
			String term = splitted[0].trim();
			int frequency = Integer.parseInt(splitted[1].trim());
			LinkedList<Integer> postings = new LinkedList<Integer>();
			
			String current = null;
			
			if(splitted[2].trim().equals(":")){
				int i = 3;
				while(i < splitted.length){
					current = splitted[i].trim();
					
					if(!current.isEmpty()){
						int docID = Integer.parseInt(current);
						postings.add(docID);
					}
					
					i++;
				}
				
				if(term != null && postings != null){
					_index.put(term, postings);
				}
			}
			
			//System.out.println(term + " " + frequency + " : " + postings.toString());
		}
	}
	
	public void uploadIndex(){
		
		if(_filePath != null && _type != null){
			
			String filepath = _filePath + File.separator + _name + ".txt";
			FileReader fileReader;
			int i = 0;
			long startTime = System.currentTimeMillis();
			
			try {
				fileReader = new FileReader(filepath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line = "";
				
				while((line = bufferedReader.readLine()) != null){
					if(line.trim().length() != 0){
						process(line);
						i++;
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long estimatedTime = System.currentTimeMillis() - startTime;
		
			System.out.println("INDEX UPLOADED : " + estimatedTime);
			System.out.println(i + " ENTRIES READ");
			System.out.println("END");
		}
	}
}
