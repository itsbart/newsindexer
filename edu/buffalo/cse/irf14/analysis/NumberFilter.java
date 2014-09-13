package edu.buffalo.cse.irf14.analysis;


/**
	Any number that is not a date should be removed.
 */


public class NumberFilter extends TokenFilter{

	public NumberFilter(TokenStream stream) {
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
