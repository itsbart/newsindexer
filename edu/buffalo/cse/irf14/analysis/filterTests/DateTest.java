package edu.buffalo.cse.irf14.analysis.filterTests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class DateTest {

	public final String defYear = "dupa";
	
	@Test
	public void test() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add(1);
		list.add(4);
		list.add(5);
		list.add(7);
		list.add(8);
		
		System.out.println(list.toString());
		
	}
}