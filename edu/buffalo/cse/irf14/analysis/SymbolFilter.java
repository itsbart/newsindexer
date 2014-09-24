package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.regex.Pattern;


/**
   It should act on the following symbols with actions as described:
   Any punctuation marks that possibly mark the end of a sentence (. ! ?) should be removed. 
   Obviously if the symbol appears within a token it should be retained (a.out for example).
   Any possessive apostrophes should be removed (‘s s’ or just ‘ at the end of a word). 
   Common contractions should be replaced with expanded forms but treated as one token. 
   (e.g. should’ve => should have). All other apostrophes should be removed.
   If a hyphen occurs within a alphanumeric token it should be retained 
   (B-52, at least one of the two constituents must have a number). 
   If both are alphabetic, it should be replaced with a whitespace and retained 
   as a single token (week-day => week day). Any other hyphens padded by spaces on either or 
   both sides should be removed.
 */


public class SymbolFilter extends TokenFilter {

	TokenStream _input;
	ArrayList<Token> _filtered;
	ContractionList list;
	String currentString;
	
	
	public SymbolFilter(TokenStream stream) {
		super(stream);
		_input = stream;
		_filtered = new ArrayList<Token>();
		list = ContractionList.getInstance();
		currentString = null;
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_input.hasNext()){
			Token current = _input.next();
			currentString = current.getTermText();
			
			//before stemming
			if(currentString != null && !currentString.isEmpty()){
				step1(); step2(); step3(); step4();
			}
			
			if(!currentString.isEmpty()){
				current.setTermText(currentString);
				_filtered.add(current);
			}
			
			return true;
		}
		
		return false;
	}

	//remove punctuation
	public void step1(){
		
		if(currentString != null && !currentString.isEmpty()){
			//currentString = UNDESIRABLES.matcher(currentString).replaceAll("");
			
			char[] charBuff = currentString.toCharArray();	
			boolean doloop = true;
			int i = charBuff.length - 1;
			
			while(doloop){
				
				//check for cases
				if(charBuff[i] == '?' || charBuff[i] == '!' ||
					charBuff[i] == '.' || charBuff[i] == ',' 
					|| charBuff[i] == ';'){
					
					i--;
										
				}else{
					doloop = false;
				}
				
				if(i < 0){
					break;
				}
				
			}
			
			//cut off 
			if(i >= 0 && i < charBuff.length - 1){
				char[] newBuffer = new char[i + 1];
				System.arraycopy(charBuff, 0, newBuffer, 0, i + 1);
				currentString = String.valueOf(newBuffer);
			}
			
		}
	}
	
	
	//remove trailing 's | s' | '
	public void step3(){
		if(currentString != null && !currentString.isEmpty()){
			
			if(currentString.endsWith("'s")){
				currentString = currentString.substring(0, currentString.length() - 2);
			}
			
			if(currentString.endsWith("'")){
				currentString = currentString.substring(0, currentString.length() - 1);
			}
			
			
		}
	}
	
	//substitue with contraction
	public void step2(){
		if(currentString != null && !currentString.isEmpty()){
			if(list.is(currentString.toLowerCase())){
				char c = currentString.charAt(0);
				currentString = list.get(currentString.toLowerCase());
				if(Character.isUpperCase(c)){
					currentString = c + currentString.substring(1);
				}
			}
			if(currentString.endsWith("'em")){
				currentString = currentString.substring(0, currentString.length() - 3).concat("them");
			}
			
			
		}
	}
	
	//remove hyphen AND any apostrophes
	public void step4(){
		if(currentString != null && !currentString.isEmpty()){
			if(currentString.contains("-")){
				String[] splitted = currentString.split("-");
				boolean isDigit = false;
				
				for(String s : splitted){
					if(isDigit = checkNumber(s)){
						break;
					}
				}
				
				if(!isDigit){
					currentString = currentString.replace("-", " ").trim();
				}
			}
			
			if(currentString.contains("'")){
				currentString = currentString.replace("'", "");
			}
			
		}
	}
	
	
	public boolean checkNumber(String s){
		
		boolean containsDigit = false;
		for(Character c : s.toCharArray()){
			if(containsDigit = Character.isDigit(c)){
				break;
			}
		}
	
		return containsDigit;
	}
	

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return new TokenStream(_filtered);
	}

}
