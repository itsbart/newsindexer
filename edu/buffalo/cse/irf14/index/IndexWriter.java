/**
 
 *
 
 */

package edu.buffalo.cse.irf14.index;



import java.io.BufferedWriter;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import java.io.PrintWriter;



import edu.buffalo.cse.irf14.document.Document;

import edu.buffalo.cse.irf14.document.FieldNames;



/**
 
 * @author nikhillo Class responsible for writing indexes to disk
 
 */

public class IndexWriter {
    
    String mIndexDir;
    
    /*
     
     * Each ID constant represents a location in the ids array
     
     * We'll increment each element of the ids array when we build the dictionary
     
     * I created an array because I didn't know increment the ids permanently
     
     * without an array that maintained a reference
     
     */
    
    private static final int DOC_ID = 0;
    
    private static final int TERM_ID = 1;
    
    private static final int AUTHOR_ID = 2;
    
    private static final int CATEGORY_ID = 3;
    
    private static final int PLACE_ID = 4;
    
    int[] ids = {0,0,0,0,0};
    
    
    
    /**
     
     * Default constructor
     
     *
     
     * @param indexDir
     
     *            : The root directory to be sued for indexing
     
     */
    
    public IndexWriter(String indexDir) {
        
        // TODO : YOU MUST IMPLEMENT THIS
        
        mIndexDir = indexDir;
        
    }
    
    
    
    /**
     
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
        
        // TODO : YOU MUST IMPLEMENT THIS
        
        buildFileDictionary(d);
        
        buildPlaceDictionary(d);
        
        buildCategoryDictionary(d);
        
    }
    
    
    
    /**
     
     * Method that indicates that all open resources must be closed and cleaned
     
     * and that the entire indexing operation has been completed.
     
     *
     
     * @throws IndexerException
     
     *             : In case any error occurs
     
     */
    
    public void close() throws IndexerException {
        
        // TODO
        
    }
    
    /*
     
     * Methods for building the Indexes
     
     */
    
    private void buildTermIndex() {
        
        
    }
    
    
    
    private void buildAuthorIndex() {
        
        
        
    }
    
    
    
    private void buildCategoryIndex() {
        
        
        
    }
    
    
    
    private void buildPlaceIndex(Document d) {
        
        
        
    }
    
    
    
    /*
     
     * Methods for building the dictionaries
     
     */
    
    private void buildTermDictionary() {
        
        
    }
    
    
    
    private void buildAuthorDictionary() {
        
    }
    
    
    
    private void buildCategoryDictionary(Document d) {
        
        String[] categories = d.getField(FieldNames.CATEGORY);
        
        String category = categories[0];
        
        String filePath = mIndexDir + "/categoryDictionary";
        
        String dictionaryEntry = category + "\t" + ids[CATEGORY_ID] + "\n";
        
        createDictionaryFile(filePath, dictionaryEntry, CATEGORY_ID);
        
    }
    
    private void buildPlaceDictionary(Document d){
        
        String[] places = d.getField(FieldNames.PLACE);
        
        String place = places[0];
        
        String filePath = mIndexDir + "/placeDictionary";
        
        String dictionaryEntry = place + "\t" + ids[PLACE_ID] + "\n";
        
        createDictionaryFile(filePath, dictionaryEntry, PLACE_ID);
        
        
    }
    
    
    private void buildFileDictionary(Document d) {
        
        String filePath = mIndexDir + "/fileDictionary"; 
        
        String[] fileIds = d.getField(FieldNames.FILEID);
        
        String fileId = fileIds[0];
        
        String dictionaryEntry = fileId + "\t" + ids[DOC_ID] + "\n";
        
        createDictionaryFile(filePath, dictionaryEntry, DOC_ID);
        
    }
    
    
    /**
     
     * Task: Creates a dictionary file
     
     * @param filePath
     
     * @param dictionaryEntry
     
     * @param id - used to identify which element of the ids array to increment
     
     * 
     
     */
    
    private void createDictionaryFile(String filePath, String dictionaryEntry, int id){
        
        File file = new File(filePath);
        
        try {
            
            if (!file.exists()) {
                
                file.createNewFile();
                
            }
            
            FileWriter fileWriter = new FileWriter(file, true);
            
            fileWriter.append(dictionaryEntry);
            
            fileWriter.close();
            
            ids[id]++;
            
            
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}