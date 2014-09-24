package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;


/**
 * A stopword removal rule. It removes tokens that occur in a standard stop list.
 */


public class StopWordFilter extends TokenFilter{
	
	TokenStream _input;
	ArrayList<Token> _filtered;
	
	
	StopWordList list = StopWordList.getInstance();
	

	public StopWordFilter(TokenStream stream) {
		super(stream);
		
		_input = stream;
		_filtered  = new ArrayList<Token>();
		
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_input.hasNext()){
			Token current = _input.next();
			String currentString = current.getTermText();
			
			if(currentString.isEmpty()){
				throw new TokenizerException();
			}
			
			if(list.is(currentString)){
				_input.remove();
				return true;
			}
			
			//does not include stopword, proceed.
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
