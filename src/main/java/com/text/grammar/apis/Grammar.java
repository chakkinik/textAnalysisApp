package com.text.grammar.apis;

public interface Grammar<T,R> {
	
	public T checkGrammarError(String text);
	
	public R process(T apiResponse,String text);
	
	public R getCorrectedSentence();
	
	public R updateCorrectedSentence(R perviousResponse);
	
	

}
