package edu.buffalo.cse.irf14.analysis;


/**
  Any character that is not a alphabet or a number and 
  does not fit the above rules should be removed.
 */


public class SpecialCharFilter extends TokenFilter{

	public SpecialCharFilter(TokenStream stream) {
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