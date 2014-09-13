package edu.buffalo.cse.irf14.analysis.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import edu.buffalo.cse.irf14.analysis.TokenStream;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;
import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;
import edu.buffalo.cse.irf14.document.Parser;
import edu.buffalo.cse.irf14.document.ParserException;

public class TokenizerTest {

	@Test
	public void tokanizerTest1() {
		String example1 = "Analyst Edward Johnson of Johnson Redbook Associates said";
		String example2 = "by the same stores which, recorded the highest, increase in sales";
		String example3 = "pct$ value added$ goods and$ services tax$ on October 1, 1986.";
		
		String[] examples = {example1, example2, example3};
		
		Tokenizer tok = new Tokenizer(",");
		
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
		
		//Correct Sizes Check
		System.out.println("SIZE tokenStream1 : " + tokenStreams[0].getTokenArray().size());
		assertEquals(1, tokenStreams[0].getTokenArray().size());
		
		
		System.out.println("SIZE tokenStream2 : " + tokenStreams[1].getTokenArray().size());
		assertEquals(3, tokenStreams[1].getTokenArray().size());
		
		
		System.out.println("SIZE tokenStream3 : " + tokenStreams[2].getTokenArray().size());
		assertEquals(2, tokenStreams[2].getTokenArray().size());
	
		//Merger
		tokenStreams[0].append(tokenStreams[1]);
		tokenStreams[0].append(tokenStreams[2]);
		assertEquals(6, tokenStreams[0].getTokenArray().size());
	
		//Removal - ptr check
		tokenStreams[0].remove();
		assertEquals(5, tokenStreams[0].getTokenArray().size());
		
		//getCurrent test
		System.out.println(tokenStreams[0].next());
		assertEquals("by the same stores which", tokenStreams[0].getCurrent().toString());
			
	}
	
	
	@Test
	public void test2(){
		
		
		String testFolder =  "/Users/Bartek/Documents/FALL2014/CSE535/training";
		String filename = testFolder + File.separatorChar + "acq" + File.separatorChar + "0000005";
		
		Tokenizer tokenizer = new Tokenizer();
		Document d = null;
		
		try {
			d = Parser.parse(filename);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] content = d.getField(FieldNames.CONTENT);
		String text = content[0];
		
		try {
			TokenStream ts = tokenizer.consume(text);
			
			while(ts.hasNext()){
				System.out.println(ts.next());
			}
	
		} catch (TokenizerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	

}
