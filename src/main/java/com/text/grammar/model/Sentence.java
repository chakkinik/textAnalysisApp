package com.text.grammar.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Sentence {

	private String message;
	private String shortMessage;
	private List<Replacement> replacements;
	private int offset;
	private int length;
	private Map<String,String> context = new HashMap<String, String>();
	private String sentence;
	private Map<String,String> type = new HashMap<String, String>();
	private Rule rule;

	
	
	
	

}
