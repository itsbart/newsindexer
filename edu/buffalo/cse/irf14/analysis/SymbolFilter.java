package edu.buffalo.cse.irf14.analysis;

public class SymbolFilter extends TokenFilter {

	TokenStream _inputStream;
	TokenStream _filtered;
	
	public SymbolFilter(TokenStream stream) {
		super(stream);
		_inputStream = stream;
	}

	@Override
	public boolean increment() throws TokenizerException {
		
		if(_inputStream.hasNext()){
			Token current = _inputStream.next();
			if(current != null){
				
			
			}else{	
				throw new TokenizerException();
			}
		}
		
		
		return false;
	}

	@Override
	public TokenStream getStream() {
		// TODO Auto-generated method stub
		return _inputStream;
	}

}
