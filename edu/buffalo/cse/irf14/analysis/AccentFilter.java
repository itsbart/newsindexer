package edu.buffalo.cse.irf14.analysis;


/**
 All accents and diacritics must be removed by folding 
 into the corresponding English characters.
 */


public class AccentFilter extends TokenFilter{

	public AccentFilter(TokenStream stream) {
		super(stream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean increment() throws TokenizerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return null;
	}

}