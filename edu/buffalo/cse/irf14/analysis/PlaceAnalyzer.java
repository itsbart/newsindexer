package edu.buffalo.cse.irf14.analysis;

public class PlaceAnalyzer implements Analyzer {

	// order
	TokenFilterType[] list = { 
			TokenFilterType.CAPITALIZATION,
			TokenFilterType.ACCENT,
			TokenFilterType.SYMBOL,
			TokenFilterType.SPECIALCHARS};

	TokenFilter currentFilter = null;
	int currentType = 0;
	TokenFilterFactory filterFactory = TokenFilterFactory.getInstance();
	TokenStream _input;

	public PlaceAnalyzer(TokenStream input) {
		_input = input;
	}

	public void runAnalyzer() throws TokenizerException {
		while (this.increment()) {
			// filter stream through all filters
		}
	}

	// hand filtered stream to another filter
	@Override
	public boolean increment() throws TokenizerException {

		if (currentType < list.length && _input != null) {
			currentFilter = filterFactory.getFilterByType(list[currentType],
					_input);

			while (currentFilter.increment()) {
				// filtering
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
