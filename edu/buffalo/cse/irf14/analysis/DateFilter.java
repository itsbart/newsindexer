package edu.buffalo.cse.irf14.analysis;

import java.util.ArrayList;


/**
 Any date occurrence should be converted to yyyymmdd format for dates and HH:mm:ss 
 for time stamps (yyyymmdd HH:mm:ss for both combined).  
 Time zones can be ignored. The following defaults should be used if any field is absent:
 - Year should be set as 1900.
 - Month should be January
 - Date should be 1st.
 - Hour, minute or second should be 00.
 */


public class DateFilter extends TokenFilter{

	ArrayList<Token> _filtered;
	TokenStream _input;
	
	//default values
	private static final String DEFAULT_YYYY = "1900";
	private static final String DEFAULT_MMDD = "01";
	private static final String DEFAULT_HHMM = "00";
	
	MonthList months = MonthList.getInstance();
	
	public DateFilter(TokenStream stream) {
		super(stream);
		
		_input = stream;
		_filtered = new ArrayList<Token>();

	}

	@Override
	public boolean increment() throws TokenizerException {
	
		while(_input.hasNext()){
			
			Token current = _input.next();
			String currentString = current.getTermText();
			
			System.out.println(currentString);
			
			//4# [YEAR] <STRING> [MONTH] 2# [DAY]
			
			//Possible sequences ? 
			//<Month> <Day>, <Year>
			//<Day> <Month> <Year>
			//<Month> <Year>
			//<Day> <Month>
			//<Month> <Day>
			//<Month>
			//<Year>
			
			//whatever matches throw to ArrayList
			
			if(months.is(currentString.toLowerCase())){
				
			}
			
			return true;
			
		}
		
		
		return false;
	}

	@Override
	public TokenStream getStream() {
		
		return null;
	}
	
}
