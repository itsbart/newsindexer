package edu.buffalo.cse.irf14.analysis.filterTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class SymbolFilterTest {

	@Test
	public void test() {
		String test = "a+b-c";
		
		String[] splitted = test.split("-");
		boolean isDigit = false;
		
		for(String s : splitted){
			if(isDigit = checkNumber(s)){
				break;
			}
		}
		
		if(!isDigit){
			test = test.replace("-", " ").trim();
		}
		
		System.out.println(test);
		
	}
	
	public boolean checkNumber(String s){
		
		boolean containsDigit = false;
		for(Character c : s.toCharArray()){
			if(containsDigit = Character.isDigit(c)){
				break;
			}
		}
	
		return containsDigit;
	}

}
