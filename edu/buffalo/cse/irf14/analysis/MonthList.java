package edu.buffalo.cse.irf14.analysis;

import java.util.HashMap;

public class MonthList {
	
	private static MonthList _instance;
	private static HashMap<String, String> _months;
	

	public MonthList(){
		
		_months = new HashMap<String, String>();
		
		_months.put("january", "01");
		_months.put("february", "02");
		_months.put("march", "03");
		_months.put("april", "04");
		_months.put("may", "05");
		_months.put("june", "06");
		_months.put("july", "07");
		_months.put("august", "08");
		_months.put("september", "09");
		_months.put("october", "10");
		_months.put("november", "11");
		_months.put("december", "12");
	}
	
	public boolean is(String key){
		return _months.containsKey(key);
	}
	
	 public String get(Character key){
		 return _months.get(key);
	 }
	 
	public static MonthList getInstance() {

		if (_instance == null) {
			_instance = new MonthList();
		}

		return _instance;
	}
	
}
