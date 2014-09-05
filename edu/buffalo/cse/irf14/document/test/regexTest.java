package edu.buffalo.cse.irf14.document.test;

import static org.junit.Assert.*;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class regexTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		String parser = "<AUTHOR>    by Simon Cox, Reuters</AUTHOR>";
		
		Pattern pattern1 = Pattern.compile("^<AUTHOR>\\u0020*By\\u0020*", Pattern.CASE_INSENSITIVE);	
		Matcher match = pattern1.matcher(parser);
		String temp = match.replaceAll("");
		
		Pattern pattern2 = Pattern.compile("</AUTHOR>$");
		Matcher match2 = pattern2.matcher(temp);
		String finalS = match2.replaceAll("");
		
		System.out.println(finalS);
		
		assertEquals("FAIL", "Simon Cox, Reuters", finalS);
		
	}

}
