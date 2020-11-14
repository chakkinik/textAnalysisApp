package com.text.grammar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.text.grammar.apis.Grammar;
import com.text.grammar.client.RestClient;
import com.text.grammar.model.CorrectedSentence;
import com.text.grammar.model.Explain;
import com.text.grammar.model.GrammarCorrectionResponse;
import com.text.grammar.model.Replacement;
import com.text.grammar.model.Sentence;

@Service
public class GrammarService implements Grammar<GrammarCorrectionResponse, CorrectedSentence> {

	@Autowired
	RestClient client;

	public final static String GRAMMARBOT_API_URL ="https://grammarbot.p.rapidapi.com/check";

	@Override
	public GrammarCorrectionResponse checkGrammarError(String text) {
		try {
			return client.getApiResponse(GRAMMARBOT_API_URL, text);
		} catch (Exception e) {
			e.printStackTrace();
			return new GrammarCorrectionResponse();
		}

	}

	@Override
	public CorrectedSentence process(GrammarCorrectionResponse apiResponse,String text) {
		
		Map<String,Explain> wordExplainMap = new HashMap<String,Explain>();
		List<Sentence> matches = apiResponse.getMatches();
		char[] charArray = text.toCharArray();
		
		for(Sentence eachMatch: matches) {
			
			int offSet = eachMatch.getOffset();
			int wordLength = eachMatch.getLength();
			
			StringBuilder wrongWord = new StringBuilder();
			
			for(int i=offSet; i<offSet+wordLength; i++ ) {
				wrongWord.append(charArray[i]);
			}
			
			List<Replacement> replacements = eachMatch.getReplacements();
			String replacedWord=null;
			if(replacements.size()>0) {
				replacedWord =replacements.get(replacements.size()-1).getValue();
			}
			
			wordExplainMap.put(wrongWord.toString(), Explain.builder().originalWord(wrongWord.toString()).replacedWord(replacedWord)
			.explaination(eachMatch.getMessage()).build());
			
		}
		
		return CorrectedSentence.builder().orginalSentence(text).explainationMap(wordExplainMap).build();
	}

	@Override
	public CorrectedSentence getCorrectedSentence() {
		return null;
	}

	@Override
	public CorrectedSentence updateCorrectedSentence(CorrectedSentence perviousResponse) {
		return null;
	}

}
