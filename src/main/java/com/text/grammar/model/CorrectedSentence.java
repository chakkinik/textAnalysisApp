package com.text.grammar.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class CorrectedSentence {
	
	private String orginalSentence;
	private String correctedSentence;
	private Set<String> incorrectWords;
	private Map<Integer,Explain> offsetMap = new HashMap<Integer, Explain>();
	private Map<String,Explain> explainationMap = new HashMap<String, Explain>();
		

}
