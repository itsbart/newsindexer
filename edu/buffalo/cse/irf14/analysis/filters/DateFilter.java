package edu.buffalo.cse.irf14.analysis.filters;

import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

/**
 Any date occurrence should be converted to yyyymmdd format for dates and HH:mm:ss for time stamps (yyyymmdd HH:mm:ss for both combined).  Time zones can be ignored. The following defaults should be used if any field is absent:
 - Year should be set as 1900.
 - Month should be January
 - Date should be 1st.
 - Hour, minute or second should be 00.
 */


public class DateFilter extends TokenFilter{

	public DateFilter(TokenStream stream) {
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
