package com.text.grammar;

public class TestReplace {

	/*
	 * a) get the words with error and suggest replace ment
	 * 
	 */
	
	public static void main(String[] args) {
		String str= "The man jumped into a black sedan"+
				"and he drove away before being noticed";
		
		String str1="The verb 'loving' is normally not used in the progressive form. Try a simple form instead.";
		
		String replace ="sedan, and";
		
		
			String[] split = str1.split(" ");
			
			for(String each: split) {
				if(each.startsWith("'") && each.endsWith("'")) {
					System.out.println(each);
				}
			}
		
		System.out.println();
				
	}
}
