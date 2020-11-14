package com.text.grammar.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GrammarCorrectionResponse {
	
	private Map<String,String> software = new HashMap<String, String>();
	private Map<String,String> warnings = new HashMap<String, String>();
	private Language language;
	private List<Sentence> matches ;

}
