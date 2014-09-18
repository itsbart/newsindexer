package edu.buffalo.cse.irf14.analysis;

import java.util.HashMap;

public class AccentList {

	
	private static AccentList _instance = null;
	private static HashMap<Character, String> _map;
	
	
	
	public AccentList() {
		
		_map = new HashMap<Character, String>();
		//Upper Case
		
		_map.put('\u00C0', "A");
		_map.put('\u00C1', "A");
		_map.put('\u00C2', "A");
		_map.put('\u00C3', "A");
		_map.put('\u00C4', "A");
		_map.put('\u00C5', "A");
		_map.put('\u00C6', "AE");
		_map.put('\u00C7', "C");
		_map.put('\u00C8', "E");
		_map.put('\u00C9', "E");
		_map.put('\u00CA', "E");
		_map.put('\u00CB', "E");
		_map.put('\u00CC', "I");
		_map.put('\u00CD', "I");
		_map.put('\u00CE', "I");
		_map.put('\u00CF', "I");
		_map.put('\u0132', "IJ");
		_map.put('\u00D0', "D");
		_map.put('\u00D1', "N");
		_map.put('\u00D2', "O");
		_map.put('\u00D3', "O");
		_map.put('\u00D4', "O");
		_map.put('\u00D5', "O");
		_map.put('\u00D6', "O");
		_map.put('\u00D8', "O");
		_map.put('\u0152', "OE");
		_map.put('\u00DE', "TH");
		_map.put('\u00D9', "U");
		_map.put('\u00DA', "U");
		_map.put('\u00DB', "U");
		_map.put('\u00DC', "U");
		_map.put('\u00DD', "Y");
		_map.put('\u0178', "Y");
		
		//lower case - just in case
		
		_map.put('\u00E0', "a");
		_map.put('\u00E1', "a");
		_map.put('\u00E2', "a");
		_map.put('\u00E3', "a");
		_map.put('\u00E4', "a");
		_map.put('\u00E5', "a");
		_map.put('\u00E6', "ae");
		_map.put('\u00E7', "c");
		_map.put('\u00E8', "e");
		_map.put('\u00E9', "e");
		_map.put('\u00EA', "e");
		_map.put('\u00EB', "e");
		_map.put('\u00EC', "i");
		_map.put('\u00ED', "i");
		_map.put('\u00EE', "i");
		_map.put('\u00EF', "i");
		_map.put('\u0133', "ij");
		_map.put('\u00F0', "d");
		_map.put('\u00F1', "n");
		_map.put('\u00F2', "o");
		_map.put('\u00F3', "o");
		_map.put('\u00F4', "o");
		_map.put('\u00F5', "o");
		_map.put('\u00F6', "o");
		_map.put('\u00F8', "o");
		_map.put('\u0153', "oe");
		_map.put('\u00DF', "ss");
		_map.put('\u00FE', "th");
		_map.put('\u00F9', "u");
		_map.put('\u00FA', "u");
		_map.put('\u00FB', "u");
		_map.put('\u00FC', "u");
		_map.put('\u00FD', "y");
		_map.put('\u00FF', "y");
		
		_map.put('\uFB00', "ff");
		_map.put('\uFB01', "fi");
		_map.put('\uFB02', "fl");
		_map.put('\uFB03', "ffi");
		_map.put('\uFB04', "ffl");
		_map.put('\uFB05', "ft");
		_map.put('\uFB06', "st");
				
	}
	
	 public boolean is(Character key) {
		 return _map.containsKey(key);
	 }
	 
	 public String get(Character key){
		 return _map.get(key);
	 }

	public static AccentList getInstance() {

		if (_instance == null) {
			_instance = new AccentList();
		}

		return _instance;
	}
	
	
}
