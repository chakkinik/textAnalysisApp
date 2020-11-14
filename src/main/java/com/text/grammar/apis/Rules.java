package com.text.grammar.apis;

import com.text.grammar.model.Rule;

public interface Rules {
	public void createRules(Rule rule);
	public void updateRules(Rule rule);
	public Rule getRules(String id);
	
	
}
