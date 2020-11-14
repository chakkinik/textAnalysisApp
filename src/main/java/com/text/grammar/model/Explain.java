package com.text.grammar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Explain {
	private String originalWord;
	private String replacedWord;
	private String explaination;

}
