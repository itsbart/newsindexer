package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;

public class StemmerFilter extends TokenFilter{
	
	TokenStream _input;
	ArrayList<Token> _filtered;
	
	String wordBuff; //placeholder for currently stemmed word

	public StemmerFilter(TokenStream stream) {
		super(stream);
		_filtered = new ArrayList<Token>();
				
		wordBuff = null;
	}

	@Override
	public boolean increment() throws TokenizerException {
	
		if(_input.hasNext()){
			Token current = _input.next();
			wordBuff = current.getTermText();
			
			//before stemming
			if(!wordBuff.isEmpty() && wordBuff.length() > 0){
				step1(); step2(); step3(); step4();
			}
			
			//after stemming
			if(!wordBuff.isEmpty() && wordBuff.length() > 0){
				current.setTermText(wordBuff);
				_filtered.add(current);
				return true;
			}
			
			//stemming not passed
			_input.remove();
			return true;
		}
		
		return false;
	}
	
	/*
	Step1 - takes care of plurals and -ed or -ing. e.g.
	
    caresses  ->  caress
    ponies    ->  poni
    ties      ->  ti
    caress    ->  caress
    cats      ->  cat

    feed      ->  feed
    agreed    ->  agree
    disabled  ->  disable

    matting   ->  mat
    mating    ->  mate
    meeting   ->  meet
    milling   ->  mill
    messing   ->  mess

    meetings  ->  meet 
    */
	
	public void step1() {
		
		if(wordBuff != null && !wordBuff.isEmpty()){
			
			int k = wordBuff.length();
			
			//words ending with *s : 
			if(wordBuff.endsWith("s")){
				//SSES -> SS
				if(wordBuff.endsWith("sses")) 
					wordBuff = wordBuff.substring(0, k - 2);
				//IES -> I
				else if(wordBuff.endsWith("ies"))	
					wordBuff = wordBuff.substring(0, k - 2);
				//S -> ""
				else if(wordBuff.charAt(k - 2) != 's')
					wordBuff = wordBuff.substring(0, k - 1);
			
			//words ending with *eed
			}else if(wordBuff.endsWith("eed")){
				//EED -> EE agreed -> agree
				if(k > 0) wordBuff = wordBuff.substring(0, k - 1);
			//words ending with *ed
			}else if(wordBuff.endsWith("ed")){
				String temp = wordBuff.substring(0, k - 2);
				//BL -> BLE disabled -> disable
				if(temp.endsWith("bl")) wordBuff = temp.concat("e");
				//IZ -> IZE fossilzed -> fosillize
				if(temp.endsWith("iz")) wordBuff = temp.concat("e");
			//words ending with *ing
			}else if(wordBuff.endsWith("ing")){
				String temp = wordBuff.substring(0, k - 3);
				//AT -> ATE mating -> mate
				if(temp.endsWith("at")) wordBuff = temp.concat("e");
			}
			//possible double character case ?
		}
		
	}
	
	
	/* step2() turns character y to i */
	
	public void step2(){
		
		if(wordBuff != null && !wordBuff.isEmpty()){
			int k = wordBuff.length();
			if(wordBuff.endsWith("y")){
				wordBuff = wordBuff.substring(0, k - 1).concat("i");
			}
		}
	}
	
	public void step3(){
		
		if(wordBuff != null && !wordBuff.isEmpty()){
			int k = wordBuff.length();
			char var = wordBuff.charAt(k - 2); //second char from last
			
			switch(var){
			
				case 'a' :
					if(wordBuff.endsWith("ational")) 
						wordBuff = wordBuff.substring(0, k - 7).concat("ate");
					if(wordBuff.endsWith("tional"))
						wordBuff = wordBuff.substring(0, k - 2);
					break;
				
				case 'b' :
					if(wordBuff.endsWith("enci")) 
						wordBuff = wordBuff.substring(0, k - 1).concat("e");
					if(wordBuff.endsWith("anci"))
						wordBuff = wordBuff.substring(0, k - 1).concat("e");
					break;
				
				case 'e' :
					if(wordBuff.endsWith("izer")) 
						wordBuff = wordBuff.substring(0, k - 1).concat("e");
					break;
				
				case 'l' :
					if(wordBuff.endsWith("bli")) 
						wordBuff = wordBuff.substring(0, k - 1).concat("e");
					if(wordBuff.endsWith("alli"))
						wordBuff = wordBuff.substring(0, k - 2);
					if(wordBuff.endsWith("entli")) 
						wordBuff = wordBuff.substring(0, k - 2);
					if(wordBuff.endsWith("eli"))
						wordBuff = wordBuff.substring(0, k - 2);
					if(wordBuff.endsWith("ousli"))
						wordBuff = wordBuff.substring(0, k - 2);
					break;
					
				case 'o' :
					if(wordBuff.endsWith("ization")) 
						wordBuff = wordBuff.substring(0, k - 5).concat("e");
					if(wordBuff.endsWith("ation"))
						wordBuff = wordBuff.substring(0, k - 3).concat("e");
					if(wordBuff.endsWith("ator"))
						wordBuff = wordBuff.substring(0, k - 2).concat("e");
					break;
					
				case 's' :
					if(wordBuff.endsWith("alism")) 
						wordBuff = wordBuff.substring(0, k - 3);
					if(wordBuff.endsWith("iveness")) 
						wordBuff = wordBuff.substring(0, k - 4);
					if(wordBuff.endsWith("fulness")) 
						wordBuff = wordBuff.substring(0, k - 4);
					if(wordBuff.endsWith("ousness")) 
						wordBuff = wordBuff.substring(0, k - 4);
					break;
				
				case 't' :
					if(wordBuff.endsWith("aliti")) 
						wordBuff = wordBuff.substring(0, k - 3);
					if(wordBuff.endsWith("iviti")) 
						wordBuff = wordBuff.substring(0, k - 3).concat("e");
					if(wordBuff.endsWith("biliti")) 
						wordBuff = wordBuff.substring(0, k - 5).concat("le");
					break;
				
				case 'g' :
					if(wordBuff.endsWith("logi")) 
						wordBuff = wordBuff.substring(0, k - 1);
					break;
			}
			
		}
	}
	
	/* step4() deals with -ic-, -full, -ness etc. similar strategy to step3. */
	
	public void step4(){
		
		if(wordBuff != null && !wordBuff.isEmpty()){
			int k = wordBuff.length();
			char var = wordBuff.charAt(k - 1); //last char
			
			switch(var){
				
				case 'e' :
					if(wordBuff.endsWith("icate")) 
						wordBuff = wordBuff.substring(0, k - 3);
					if(wordBuff.endsWith("ative")) 
						wordBuff = wordBuff.substring(0, k - 5);
					if(wordBuff.endsWith("alize")) 
						wordBuff = wordBuff.substring(0, k - 3);
				break;
				
				case 'i' :
					if(wordBuff.endsWith("iciti")) 
						wordBuff = wordBuff.substring(0, k - 3);
				break;
				
				case 'l' :
					if(wordBuff.endsWith("ical")) 
						wordBuff = wordBuff.substring(0, k - 2);
					if(wordBuff.endsWith("ful")) 
						wordBuff = wordBuff.substring(0, k - 3);
				break;
				
				case 's' :
					if(wordBuff.endsWith("ness")) 
						wordBuff = wordBuff.substring(0, k - 4);
					break;
			}
		}
	}
	
	//step5 needed - ending cut offs TO DO
	
	
	@Override
	public TokenStream getStream() {
		
		return null;
	}

}