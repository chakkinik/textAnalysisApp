package com.text.grammar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextAnalysisApplication {

	public static void main(String[] args) {
		
		/*
		 * String s = "Is this the personal pronoun 'I'? It is spelled uppercase";
		 * 
		 * char[] charArray = s.toCharArray();
		 * 
		 * String str = "an/"; String b="an";
		 * 
		 * String[] stringArray = str.split("\\W+");
		 * 
		 * for (int i = 0; i < stringArray.length; i++) {
		 * System.out.println(stringArray[i]); }
		 */		
		
		System.out.println("can't".contains("can hardly"));
		 String[] split = "can hardly".split("//W+");
		 for(String s:split) {
			 System.out.println(s);
		 }
	 SpringApplication.run(TextAnalysisApplication.class, args);
	}

}
