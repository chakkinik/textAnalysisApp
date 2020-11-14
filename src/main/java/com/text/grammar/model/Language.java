package com.text.grammar.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {
 private String name;
 private String code;
 private Map<String,String> detectedLanguge = new HashMap<String, String>();
}
