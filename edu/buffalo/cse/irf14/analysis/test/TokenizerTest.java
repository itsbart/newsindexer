package edu.buffalo.cse.irf14.analysis.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;

public class TokenizerTest {

	@Test
	public void tokanizerTest1() {
		String example1 = "Analyst Edward Johnson of Johnson Redbook Associates said";
		String example2 = "by the same stores which, recorded the highest, increase in sales";
		String example3 = "pct$ value added$ goods and$ services tax$ on October 1, 1986.";
		
		String[] examples = {example1, example2, example3};
		
		Tokenizer tok = new Tokenizer(",");
		
		TokenStream tokenStream = null;
		TokenStream[] tokenStreams = new TokenStream[3];
		int i = 0;
		
		for(String input : examples){
			try {
				tokenStreams[i] = tok.consume(input);
				
				while(tokenStreams[i].hasNext()){
					System.out.println(tokenStreams[i].next().toString());
				}
			
				tokenStreams[i].reset();
				i++;
				
			} catch (TokenizerException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("SIZE tokenStream1 : " + tokenStreams[0].getTokenArray().size());
		System.out.println("SIZE tokenStream2 : " + tokenStreams[1].getTokenArray().size());
		System.out.println("SIZE tokenStream3 : " + tokenStreams[2].getTokenArray().size());
	
		//Merger
		
		tokenStreams[0].append(tokenStreams[1]);
		tokenStreams[0].append(tokenStreams[2]);
	
		System.out.println("SIZE tokenStream1 : " + tokenStreams[0].getTokenArray().size());
		tokenStreams[0].remove();
		System.out.println("SIZE tokenStream1 : " + tokenStreams[0].getTokenArray().size());
		System.out.println(tokenStreams[0].next());
	}

}
