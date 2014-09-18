package edu.buffalo.cse.irf14.document.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import sun.rmi.runtime.Log;
import edu.buffalo.cse.irf14.analysis.Tokenizer;
import edu.buffalo.cse.irf14.analysis.TokenizerException;


/**
 * Unit test for REGEX parsers
 * Please use for different regex expressions
 */

public class regexTest {

	@Test
	public void authorParsing() {
	
		/** VERSION 2 - NEW **/
		
		String example1 = "<AUTHOR>    by Simon Cox and Stephen King, Reuters</AUTHOR>";
		String example2 = "<AUTHOR>    by Stephen King, Reuters</AUTHOR>";
		String example3 = "<AUTHOR>    by Stephen King</AUTHOR>";
		
		String[] examples = {example1, example2, example3};
		String[] results = {"Simon Cox and Stephen King, Reuters", "Stephen King, Reuters",
				"Stephen King"
		};
		
		Pattern pattern = Pattern.compile("^<AUTHOR>\\u0020*by\\u0020(.*)</AUTHOR>$", Pattern.CASE_INSENSITIVE);
		
		for(int i = 0; i < examples.length; i++){
			Matcher m = pattern.matcher(examples[i]);
			
			if(m.find()){
				assertEquals("FAIL", results[i], m.group(1).trim());
				System.out.println(m.group(1).trim());
			}else{
				fail();
			}
			
		}
		
		String[] authors = results[0].split("\\u0020and|AND\\u0020");
		//if this one has 2 entries then two authors
		ArrayList<String> result = new ArrayList<String>();
		
		
		if(authors.length > 1) {
			result.add(authors[0].trim());
			String[] authorAndOrg = authors[1].split(",");
			result.add(authorAndOrg[0].trim());
		}else{
			String[] authorAndOrg = authors[0].split(",");
			result.add(authorAndOrg[0].trim());
		}
		
		for(String output : result){
			System.out.println(output);
		}
		
		
		String input = "camelCase";
		Pattern pattern5 = Pattern.compile("^[a-z]+[A-Z]+.*");
		Matcher matcher = pattern5.matcher(input);
		
		if(matcher.find()){
			System.out.println("MATCH!");
		}
		
		String input2 = "End.";
		if(input2.endsWith("End.")){
			System.out.println("END OF SENTENCE!");
			input2 = input2.substring(0,0);
		}
				
		
	}

}
