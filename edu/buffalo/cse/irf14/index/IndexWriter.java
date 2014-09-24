/**
 
 *
 
 */

package edu.buffalo.cse.irf14.index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.buffalo.cse.irf14.analysis.Analyzer;
import edu.buffalo.cse.irf14.analysis.AnalyzerFactory;
import edu.buffalo.cse.irf14.analysis.Token;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenFilterFactory;
import edu.buffalo.cse.irf14.analysis.TokenFilterType;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;
import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;

/**
 * 
 * @author nikhillo Class responsible for writing indexes to disk
 */

public class IndexWriter {

	AnalyzerFactory analyzerFactory = AnalyzerFactory.getInstance();

	String mIndexDir;
	TreeMap<String, LinkedList<Integer>> authorIndex;
	TreeMap<String, LinkedList<Integer>> categoryIndex;
	TreeMap<String, LinkedList<Integer>> placeIndex;
	TreeMap<String, LinkedList<Integer>> termIndex;

	ArrayList<Document> documentDictionary;	
	HashMap<String, Integer> termFrequency;
	TokenFilterFactory factory = TokenFilterFactory.getInstance();

	public enum IndexNames {
		AUTHOR_INDEX, CATEGORY_INDEX, PLACE_INDEX, TERM_INDEX
	};

	int[] ids = { 0, 0, 0, 0, 0 };

	/**
	 * Default constructor
	 * 
	 * @param indexDir
	 *            The root directory to be sued for indexing
	 */

	public IndexWriter(String indexDir) {

		// TODO : YOU MUST IMPLEMENT THIS
		mIndexDir = indexDir;
		documentDictionary = new ArrayList<Document>();

		// index initialization
		authorIndex = new TreeMap<String, LinkedList<Integer>>();
		categoryIndex = new TreeMap<String, LinkedList<Integer>>();
		placeIndex = new TreeMap<String, LinkedList<Integer>>();
		termIndex = new TreeMap<String, LinkedList<Integer>>();
		
		termFrequency = new HashMap<String, Integer>();
	}

	/**
	 * Assigns Document Id for selected document in incremental fashion
	 * 
	 * @param Document
	 *            for which we want to assign ID
	 * @return Assigned ID
	 */

	public int updateDocumentDictionary(Document d) {

		this.documentDictionary.add(d);

		// incremental fashion
		int id = documentDictionary.size();
		d.setID(id);

		return id;
	}

	/**
	 * 
	 * Method to add the given Document to the index This method should take
	 * care of reading the filed values, passing them through corresponding
	 * analyzers and then indexing the results for each indexable field within
	 * the document.
	 * 
	 * @param d
	 *            : The Document to be added
	 * @throws IndexerException
	 *             : In case any error occurs
	 */

	public void addDocument(Document d) throws IndexerException {

		if (d != null) {

			updateDocumentDictionary(d);

			// assign docid
			try {
				buildAuthorIndex(d.getField(FieldNames.AUTHOR), d.getID());
				buildCategoryIndex(d.getField(FieldNames.CATEGORY), d.getID());
				buildPlaceIndex(d.getField(FieldNames.PLACE), d.getID());
				buildTermIndex(d.getField(FieldNames.CONTENT), d.getID());

			} catch (TokenizerException e) {
				e.printStackTrace();
			}

		} else {
			return;
		}

		/*
		 * // TODO : YOU MUST IMPLEMENT THIS buildFileDictionary(d);
		 * buildPlaceDictionary(d); buildCategoryDictionary(d);
		 */

	}

	/**
	 * 
	 * Method responsible for updating author Index. Tokenizes authors and uses
	 * author analyzer to filter out tokens
	 * 
	 * @param authors
	 *            : String array containing one or more authors, docID: document
	 *            ID of current doc
	 * @throws IndexerException
	 *             : In case any error occurs
	 */

	public void buildAuthorIndex(String[] authors, int docID)
			throws TokenizerException {

		if (authors != null) {
			Tokenizer tokenizer = new Tokenizer();

			for (String author : authors) {
				TokenStream ts = tokenizer.consume(author);

				// use analyzer : on ts
				// abstractFactory.getAnalyzer(Author)
				// analyze tokens

				// reconstruct filtered name from tokenFilter
				ts.reset();
				String finalName = "";
				while (ts.hasNext()) {
					finalName += ts.next() + " ";
				}

				if (!appendIndex(finalName.trim(), docID, FieldNames.AUTHOR)) {
					throw new TokenizerException();
				}
			}
		}
	}

	/**
	 * 
	 * Method responsible for updating category Index.
	 * 
	 * @param cat
	 *            : String array containing category, docID: document ID of
	 *            current doc
	 * @throws IndexerException
	 *             : In case any error occurs
	 */

	public void buildCategoryIndex(String[] cat, int docID)
			throws TokenizerException {

		if (cat != null) {

			// Tokenizer tokenizer = new Tokenizer();
			String category = cat[0];

			// we dont have to analyze category ?
			// TokenStream ts = tokenizer.consume(category);
			// to lower case analyzer at least

			if (!appendIndex(category.trim(), docID, FieldNames.CATEGORY)) {
				throw new TokenizerException();
			}

		} else {
			throw new TokenizerException();
		}
	}

	/**
	 * 
	 * Method responsible for updating place Index. Uses PlaceAnalyzer to filter
	 * out tokens.
	 * 
	 * @param Place
	 *            : String array containing place, docID: document ID of current
	 *            doc
	 * @throws IndexerException
	 *             : In case any error occurs
	 */

	public void buildPlaceIndex(String[] Place, int docID)
			throws TokenizerException {

		if (Place != null) {
			String place = Place[0];

			//dummy delimiter - want to save entire token
			Tokenizer tokenizer = new Tokenizer("#");
			TokenStream ts = tokenizer.consume(place);
			
			Analyzer placeAnalyzer = analyzerFactory.getAnalyzerForField(FieldNames.PLACE, ts);

			while(placeAnalyzer.increment()){
				//filtering
			}
			ts = placeAnalyzer.getStream();
			
			while(ts.hasNext()){
				if (!appendIndex(ts.next().toString(), docID, FieldNames.PLACE)) {
				throw new TokenizerException();
				}
			}

		} else {
			return;
		}
	}

	/**
	 * 
	 * Method responsible for updating term Index. Uses TermAnalyzer to filter
	 * out tokens into proper form.
	 * 
	 * @param Content
	 *            : String array containing entire content, docID: document ID
	 *            of current doc
	 * @throws IndexerException
	 *             : In case any error occurs
	 */

	public void buildTermIndex(String[] Content, int docID)
			throws TokenizerException {

		if (Content != null && Content[0] != null && !Content[0].isEmpty()) {

			String content = Content[0];

			Tokenizer tokenizer = new Tokenizer();
			TokenStream ts = tokenizer.consume(content);

			Analyzer contentAnalysis =
			analyzerFactory.getAnalyzerForField(FieldNames.CONTENT, ts);

			while(contentAnalysis.increment()){
			}

			ts = contentAnalysis.getStream();

			while (ts.hasNext()) {
				if (!appendIndex(ts.next().toString().trim(), docID,
						FieldNames.CONTENT)) {
					throw new TokenizerException();
				}
			}

		} else {
			return;
		}
	}

	/**
	 * 
	 * Helper method used for updating respective treeMaps
	 * 
	 * @param key
	 *            : String key, value : docID, indexType: fieldName of index we
	 *            want to update
	 * @return true: operation successful false: error
	 */

	public boolean appendIndex(String key, int value, FieldNames indexType) {

		TreeMap<String, LinkedList<Integer>> index = null;

		// find index
		switch (indexType) {

		case AUTHOR:
			index = this.authorIndex;
			break;

		case CATEGORY:
			index = this.categoryIndex;
			break;

		case PLACE:
			index = this.placeIndex;
			break;

		case CONTENT:
			index = this.termIndex;
			break;

		default:
			return false;
		}

		if (index.containsKey(key)) {
			
			//check if last value in linkedlist is same as this
		if(index.get(key).getLast() == value){
				//increase frequency
				termFrequency.put(key, termFrequency.get(key) + 1);
			}else{
				index.get(key).add(value);
				termFrequency.put(key, termFrequency.get(key) + 1);
			}
			
		} else {
			LinkedList<Integer> listing = new LinkedList<Integer>();
			listing.add(value);
			index.put(key, listing);
			termFrequency.put(key, 1);
		}
		return true;
	}

	/**
	 * 
	 * Method that indicates that all open resources must be closed and cleaned
	 * and that the entire indexing operation has been completed.
	 * 
	 * @throws TokenizerException
	 * @throws IndexerException
	 *             : In case any error occurs
	 * 
	 */

	public void close() throws IndexerException {

		TreeMap<String, LinkedList<Integer>> index = null;

		IndexNames[] indexes = IndexNames.values();
		for (IndexNames name : indexes) {

			switch (name) {

			case AUTHOR_INDEX:
				index = authorIndex;
				break;

			case CATEGORY_INDEX:
				index = categoryIndex;
				break;

			case PLACE_INDEX:
				index = placeIndex;
				break;

			case TERM_INDEX:
				index = termIndex;
				break;

			default:
				return;
			}

			try {

				long startTime = System.currentTimeMillis();

				File file = new File(mIndexDir + "/" + name.toString() + ".txt");

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				for (Entry<String, LinkedList<Integer>> entry : index
						.entrySet()) {
					bw.write(entry.getKey() + " ");
					bw.write(termFrequency.get(entry.getKey()) + " : ");
					
					// StringBuilder builder = new StringBuilder();
					
					for(Integer value : entry.getValue()){
						bw.write(value.toString());
						bw.write(" ");
					}
					
					//bw.write(entry.getValue().toString());
					// listing = listing.replaceAll(",$", "");
					// builder.append("null");
					// bw.write(builder.toString());
					bw.newLine();
				}

				bw.flush();
				bw.close();

				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("FINISHED INDEXING: " + name.toString()
						+ " TIME: " + estimatedTime + "ms");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("AUTHOR SIZE: " + authorIndex.size());
		System.out.println("CATEGORY SIZE: " + categoryIndex.size());
		System.out.println("PLACE SIZE: " + placeIndex.size());
		System.out.println("TERM SIZE: " + termIndex.size());

		getStats();

	}

	public static void getStats() {
		
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
		sb.append("allocated memory: " + format.format(allocatedMemory / 1024)
				+ "<br/>");
		sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
		sb.append("total free memory: "
				+ format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024)
				+ "<br/>");
		
		System.out.println(sb.toString());
		
	}

}
