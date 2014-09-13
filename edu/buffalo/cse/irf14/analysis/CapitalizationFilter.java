package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All tokens should be lowercased unless: - The whole word is in caps (AIDS
 * etc.) and the whole sentence is not in caps - The word is camel cased and is
 * not the first word in a sentence. - If adjacent tokens satisfy the above
 * rule, they should be combined into a single token (San Francisco, Brad Pitt,
 * etc.)
 */

/*
	TO DO: SOME CHECKER to see if entire sentence not capitalized. 
*/

public class CapitalizationFilter extends TokenFilter {

	TokenStream _input;
	ArrayList<Token> _filtered;

	// previously processed token
	String _previous = null;

	// counter keeping track of number of words in a sentence
	int counter = 1;

	// Pattern for CamelCase
	public Pattern pattern = Pattern.compile("^[a-z]+[A-Z]+.*");

	public CapitalizationFilter(TokenStream stream) {
		super(stream);

		_input = stream;
		_filtered = new ArrayList<Token>();
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_input.hasNext()){
			
			Token current = _input.next();
			String currentString = current.getTermText();
			
			Matcher matcher = pattern.matcher(currentString);
			
			
			//CASE: Merger for words like San + Francisco
			if(counter  > 1 && _previous != null && 
					Character.isUpperCase(_previous.charAt(0)) 
						&& Character.isUpperCase(currentString.charAt(0)) && 
						currentString.length() > 1){
					
					_filtered.get(_filtered.size() - 1).setTermText(_previous + " ");
					_filtered.get(_filtered.size() - 1).merge(current);
				
			//CASE: CamelCase word, must not be first word in sentence
			}else if((counter > 1 && matcher.find()) || 
					currentString.equals(currentString.toUpperCase())){

					//dont change it, store as is
					_filtered.add(current);
					
			//CASE: Capitalized Case like "California", "Apple" as long as its not the first word
			
			}else if(counter > 1 && Character.isUpperCase(currentString.charAt(0)) &&
					currentString.substring(1).equals(currentString.substring(1).toLowerCase())){
					
					//dont change it, store as is
					_filtered.add(current);
					
			//CASE: General Case, just to lower and store
			}else{
					_previous = currentString;
					current.setTermText(currentString.toLowerCase());
					_filtered.add(current);
			}
					
					//store old reference
					_previous = currentString;
					
					if(currentString.endsWith(".")){
						//first word in sentence
						counter = 1;
					}else{
						counter++;
					}
					
					return true;
		}
		
		return false;
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return new TokenStream(_filtered);
	}

}
