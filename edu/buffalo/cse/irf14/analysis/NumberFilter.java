package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;


/**
	Any number that is not a date should be removed.
 */


public class NumberFilter extends TokenFilter{

	TokenStream _input;
	ArrayList<Token> _filtered;
	
	public NumberFilter(TokenStream stream) {
		super(stream);
		
		_input = stream;
		_filtered = new ArrayList<Token>();
	
	}

	@Override
	public boolean increment() throws TokenizerException {

		if(_input.hasNext()){
			
			Token current = _input.next();
			char[] charBuff = current.getTermBuffer();
			
			boolean prevNumber = false; //flag if previous was number
			
			
			for (int i = 0; i < charBuff.length; i++){
				if((Character.isDigit(charBuff[i])) || 
						(prevNumber && (charBuff[i] == '.' || 
								charBuff[i] == ','))){
					
					char[] newBuffer = new char[charBuff.length - 1];
					System.arraycopy(charBuff, 0, newBuffer, 0, i);
					System.arraycopy(charBuff, i+1, newBuffer, i, charBuff.length - i - 1);
					i--;
					charBuff = newBuffer;
					prevNumber = true;
					
				}else{
					prevNumber = false;
				}
			}
			
			if(charBuff.length > 0){
				current.setTermBuffer(charBuff);
				_filtered.add(current);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public TokenStream getStream() {
		return new TokenStream(_filtered);
	}

}
