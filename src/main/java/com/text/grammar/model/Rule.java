package com.text.grammar.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Rule {
	private String id;
	private String subId;
	private String desc;
	private String issueType;
	private Map<String, String> category = new HashMap<String, String>();
		
}
