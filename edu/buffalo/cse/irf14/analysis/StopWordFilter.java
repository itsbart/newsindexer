package edu.buffalo.cse.irf14.analysis;


/**
 * A stopword removal rule. It removes tokens that occur in a standard stop list.
 */


public class StopWordFilter extends TokenFilter{

	public StopWordFilter(TokenStream stream) {
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
