package edu.buffalo.cse.irf14.index.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.buffalo.cse.irf14.index.IndexReader;
import edu.buffalo.cse.irf14.index.IndexType;

public class IndexUploadTest {

	@Test
	public void test() {
		IndexReader reader = new IndexReader("/Users/Bartek/Documents/FALL2014/CSE535", IndexType.TERM);
	}

}
