package edu.buffalo.cse.irf14.analysis.filterTests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.buffalo.cse.irf14.analysis.CapitalizationFilter;
import edu.buffalo.cse.irf14.analysis.TokenFilter;
import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class CapitalizationTester {

	@Test
	public void test() {
		
		String input = "This is San Francisco I have Lived in. "
				+ "There are Some People in The World. AIDS is horrible diesease";
		
		Tokenizer tokenizer = new Tokenizer();
		try {
			TokenStream ts = tokenizer.consume(input);
			TokenFilter filter = new CapitalizationFilter(ts);
			
			while(filter.increment()){
				//filter out everything
			}
			
			TokenStream filtered = filter.getStream();
			filtered.reset();
			
			String result = "";
			
			while(filtered.hasNext()){
				result += filtered.next() + " ";
			}
			
			System.out.println("ARRAY: " + filtered.getTokenArray().toString());
			
			System.out.println(result.trim());
			
			
		} catch (TokenizerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
