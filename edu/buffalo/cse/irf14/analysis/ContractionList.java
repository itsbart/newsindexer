package edu.buffalo.cse.irf14.analysis;

import java.util.HashMap;

public class ContractionList {
	
	private static HashMap<String, String> _map;
	private static ContractionList _instance;
	
	public ContractionList(){
		
		//http://en.wikipedia.org/wiki/Wikipedia:List_of_English_contractions
		_map = new HashMap<String, String>();
		
		_map.put("aren't", "are not");
		
		_map.put("aren't", "are not");
		_map.put("can't", "cannot");
		_map.put("could've", "could have");
		_map.put("couldn't", "could not");
		_map.put("couldn't've", "could not have");
		_map.put("didn't", "did not");
		_map.put("doesn't", "does not");
		_map.put("don't", "do not");
		_map.put("hadn't", "had not");
		_map.put("hadn't've", "had not have");
		_map.put("hasn't", "has not");
		_map.put("haven't", "have not");
		_map.put("he'd", "he would");
		_map.put("he'd've", "he would have");
		_map.put("he'll", "he will");
		_map.put("he's", "he is");
		_map.put("how'd", "how did");
		_map.put("how'll", "how will");
		_map.put("how's", "how is");
		_map.put("how's", "how does");
		_map.put("i'd", "i would");
		_map.put("i'd've", "i would have");
		_map.put("i'll", "i will");
		_map.put("i'm", "i am");
		_map.put("i've", "i have");
		_map.put("isn't", "is not");
		_map.put("it'd", "it would");
		_map.put("it'd've ", "it would have");
		_map.put("it'll", "it will");
		_map.put("it's", "it is");
		_map.put("let's", "let us");
		_map.put("ma'am", "madam");
		_map.put("mightn't", "might not");
		_map.put("mightn't've", "might not have");
		_map.put("might've", "might have");
		_map.put("mustn't", "must not");
		_map.put("must've", "must have");
		_map.put("needn't", "need not");
		_map.put("not've", "not have");
		_map.put("shan't", "shall not");
		_map.put("she'd", "she would");
		_map.put("she'd've", "she would have");
		_map.put("she'll", "she will");
		_map.put("she's", "she is");
		_map.put("should've", "should have");
		_map.put("shouldn't", "should not");
		_map.put("shouldn't've", "should not have");
		_map.put("that's", "that is");
		_map.put("there'd", "there would");
		_map.put("there'd've", "there would have");
		_map.put("there're", "there are");
		_map.put("there's", "there is");
		_map.put("they'd", "they would");
		_map.put("they'd've", "they would have");
		_map.put("they'll", "they will");
		_map.put("they're", "they are");
		_map.put("they've", "they have");
		_map.put("wasn't", "was not");
		_map.put("we'd", "we would");
		_map.put("we'd've", "we would have");
		_map.put("we'll", "we will");
		_map.put("we're", "we are");
		_map.put("we've", "we have");
		_map.put("weren't", "were not");
		_map.put("what'll", "what will");
		_map.put("what're", "what are");
		_map.put("what's", "what is");
		_map.put("what've", "what have");
		_map.put("when's", "when is");
		_map.put("where'd", "where did");
		_map.put("where's", "where is");
		_map.put("where've", "where have");
		_map.put("who'd", "who would");
		_map.put("who'll", "who will");
		_map.put("who're", "who are");
		_map.put("who's", "who is");
		_map.put("who've", "who have");
		_map.put("why'll", "why will");
		_map.put("why're", "why are");
		_map.put("why's", "why is");
		_map.put("won't", "will not");
		_map.put("would've", "would have");
		_map.put("wouldn't", "would not");
		_map.put("wouldn't've", "would not have");
		_map.put("y'all", "you all");
		_map.put("y'all'd've", "you all should have");
		_map.put("you'd", "you would");
		_map.put("you'd've", "you would have");
		_map.put("you'll", "you will");
		_map.put("you're", "you are");
		_map.put("you've", "you have");
		
	}
	
	
	
	 public boolean is(String key) {
		 return _map.containsKey(key);
	 }
	 
	 public String get(String key){
		 return _map.get(key);
	 }

	public static ContractionList getInstance() {

		if (_instance == null) {
			_instance = new ContractionList();
		}
		
		return _instance;
	}
	
	
}
