package edu.buffalo.cse.irf14.analysis.filters;

import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

/**
 All tokens should be lowercased unless:
  - The whole word is in caps (AIDS etc.) and the whole sentence is not in caps
  - The word is camel cased and is not the first word in a sentence. 
  - If adjacent tokens satisfy the above rule, they should be combined into a single token 
    (San Francisco, Brad Pitt, etc.)
*/


public class CapitalizationFilter extends TokenFilter {

	public CapitalizationFilter(TokenStream stream) {
		super(stream);
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
