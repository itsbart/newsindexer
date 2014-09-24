package edu.buffalo.cse.irf14.analysis;

public class ContentAnalyzer implements Analyzer{
	
	//order
	TokenFilterType[] list = {
			TokenFilterType.STOPWORD,
			TokenFilterType.ACCENT,
			TokenFilterType.CAPITALIZATION,
			TokenFilterType.SYMBOL,
			TokenFilterType.NUMERIC,
			TokenFilterType.SPECIALCHARS, 
			TokenFilterType.STOPWORD,
			TokenFilterType.STEMMER
			};
	
	TokenFilter currentFilter = null;
	int currentType = 0;
	TokenFilterFactory filterFactory = TokenFilterFactory.getInstance();
	TokenStream _input;
	
	public ContentAnalyzer(TokenStream input){
		_input = input;	
	}
	
	public void runAnalyzer() throws TokenizerException{
		while(this.increment()){
			//filter stream through all filters
		}
	}

	//hand filtered stream to another filter
	@Override
	public boolean increment() throws TokenizerException {
		
		if(currentType < list.length && _input != null){
			currentFilter = filterFactory.getFilterByType(list[currentType], _input);
			
			while(currentFilter.increment()){
				//filtering
			}
			
			_input = currentFilter.getStream();
			currentType++;
			
			return true;
		}
		
		
		return false;
	}

	@Override
	public TokenStream getStream() {
		return _input;
	}

}
