package edu.buffalo.cse.irf14.document.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
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
		
		char[] c = {'h', 'e', 'l', 'o'};
		char[] b = {'a', 'b', 'c', 'd'};
		
		for(int i = 0; i < c.length; i++){
			if(c[i] == 'e'){
				char[] newBuff = new char[c.length + b.length];
				System.arraycopy(c, 0, newBuff, 0, i);
				System.arraycopy(b, 0, newBuff, i, b.length);
				System.arraycopy(c, i + 1, newBuff, i + b.length, c.length - i - 1);
				c = newBuff;
			}
		}
		
		System.out.println(c);
		
	    String unicode =
	    	     "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
	    	    + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
	    	    + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
	    	    + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
	    	    + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
	    	    + "\u00C5\u00E5"                                                             
	    	    + "\u00C7\u00E7" 
	    	    + "\u0150\u0151\u0170\u0171" 
	    	    ;
		
		String test = "nа̀раà";
		for(Character ch : test.toCharArray()){
			char chr = ch;
			int pos = unicode.indexOf(chr);
			if(pos < 0){
				System.out.println("NO ENTRY");
			}else{
				System.out.println("POS "+pos+ " char " + ch);
			}
		}
		
		
		
		
	}

}
