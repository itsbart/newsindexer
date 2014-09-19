package edu.buffalo.cse.irf14.analysis.filterTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpecialCharFilter {

	@Test
	public void test() {
		
		String currentString = ".a()___---//";
		char[] charBuff = currentString.toCharArray();

		
		for(int i = 0; i < charBuff.length; i++){
			if(!Character.isLetter(charBuff[i]) && 
					!Character.isDigit(charBuff[i])){
				
				if(charBuff[i] != '.' && i > 0){
					
				}else{
				
				char[] newBuffer = new char[charBuff.length - 1];
				System.arraycopy(charBuff, 0, newBuffer, 0, i);
				System.arraycopy(charBuff, i+1, newBuffer, i, charBuff.length - i - 1);
				charBuff = newBuffer;
				i--;
				}
			}
		}
		
		
		System.out.println(charBuff);
	}
	

}
