package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;
import java.util.Arrays;


/**
 All accents and diacritics must be removed by folding 
 into the corresponding English characters.
 */


public class AccentFilter extends TokenFilter{

	TokenStream _input;
	ArrayList<Token> _filtered;
	AccentList list;
	
	
	public AccentFilter(TokenStream stream) {
		super(stream);
		
		_input = stream;
		_filtered = new ArrayList<Token>();
		list = AccentList.getInstance();
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_input.hasNext()){
			Token current = _input.next();
			char[] buffer = current.getTermBuffer();

			
			for(int i = 0; i < buffer.length; i++){
				if(list.is(buffer[i])){
					
					String subst = list.get(buffer[i]);
					
					if(subst.length() == 1){
						buffer[i] = subst.charAt(0);
					}else{
						char[] newBuffer = new char[buffer.length + subst.length()];
						char[] substChar = subst.toCharArray();
						
						//copy buffer up to i with i elements
						//copy substChars
						//copy rest of buffer to newbuffer
						System.arraycopy(buffer, 0, newBuffer, 0, i);
						System.arraycopy(substChar, 0, newBuffer, i, substChar.length);
						System.arraycopy(buffer, i + 1, newBuffer, 
								i + substChar.length, buffer.length - i - 1);
						
						buffer = newBuffer;
					}
				}
			}
			current.setTermBuffer(buffer);
			_filtered.add(current);
			return true;
		}
	
		return false;
	}

	@Override
	public TokenStream getStream() {
		return new TokenStream(_filtered);
	}

}
