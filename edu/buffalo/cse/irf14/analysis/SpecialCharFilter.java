package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;


/**
  Any character that is not a alphabet or a number and 
  does not fit the above rules should be removed.
 */


public class SpecialCharFilter extends TokenFilter{

	TokenStream _input;
	ArrayList<Token> _filtered;
	
	
	
	public SpecialCharFilter(TokenStream stream) {
		super(stream);
		_input = stream;
		_filtered = new ArrayList<Token>();
		
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_input.hasNext()){
			
			Token current = _input.next();
			String currentString = current.getTermText();
			
			//check for hyphen inclusion
			
			if(currentString.contains("-")){
				String[] splitted = currentString.split("-");
				boolean isDigit = false;
				
				for(String s : splitted){
					if(isDigit = checkNumber(s)){
						break;
					}
				}
				
				if(!isDigit){
					currentString = currentString.replace("-", "");
				}
				
			}
			
	
			
			char[] charBuff = currentString.toCharArray();
			
			//check for non letters nor numbers - excluding '.' inside word
			for(int i = 0; i < charBuff.length; i++){
				if(!Character.isLetter(charBuff[i]) && 
						!Character.isDigit(charBuff[i])){
					
					//hackish way to only ommit internal . - i know its ugly but time's money :<
					if(charBuff[i] == '.' && i > 0){
						
					}else{
						char[] newBuffer = new char[charBuff.length - 1];
						System.arraycopy(charBuff, 0, newBuffer, 0, i);
						System.arraycopy(charBuff, i+1, newBuffer, i, charBuff.length - i - 1);
						charBuff = newBuffer;
						i--;
					}
				}
			}
			
			current.setTermBuffer(charBuff);
			_filtered.add(current);
			return true;
		
		}
		
		return false;
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
