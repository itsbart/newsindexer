package edu.buffalo.cse.irf14.analysis.filterTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberTest {

	@Test
	public void test() {
		
		String number = "#.12";
		char[] charBuff = number.toCharArray();
		boolean nextNumber = false;
		
		for (int i = 0; i < charBuff.length; i++){
			if((Character.isDigit(charBuff[i])) || 
					charBuff[i] == '.' && nextNumber){
				char[] newBuffer = new char[charBuff.length - 1];
				System.arraycopy(charBuff, 0, newBuffer, 0, i);
				System.arraycopy(charBuff, i+1, newBuffer, i, charBuff.length - i - 1);
				i--;
				charBuff = newBuffer;
				nextNumber = true;
			}else{
				nextNumber = false;	
			}
		}
		
		
		System.out.println(charBuff);
		
		
	}
	

}
