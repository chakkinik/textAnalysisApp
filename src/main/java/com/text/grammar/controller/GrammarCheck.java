package com.text.grammar.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.text.grammar.model.CorrectedSentence;
import com.text.grammar.model.Explain;
import com.text.grammar.model.GrammarCorrectionResponse;
import com.text.grammar.service.GrammarService;

@RestController
@RequestMapping("/grammar")
public class GrammarCheck {

		
	@Autowired
	GrammarService grammarService;


	@GetMapping("/check")
	public CorrectedSentence check(@RequestParam String text) {

		
		GrammarCorrectionResponse grammarResponse = grammarService.checkGrammarError(text);
		

		if (grammarResponse==null || grammarResponse.getMatches().size() < 1) {
			return CorrectedSentence.builder().orginalSentence(text).build();
		}
		
		return grammarService.process(grammarResponse,text);
		

		/*Map<String, Explain> correctedWordMap = new HashMap<String, Explain>();
		Set<String> incorrectWords = new HashSet<String>();

		for (Sentence match : matches) {
			String message = match.getMessage();
			String[] messages = text.split(" ");
			// check the work
			if (message != null) {
				boolean gotError = false;
				boolean gotWord = false;
				StringBuilder actualWord = new StringBuilder();
				char[] charArray = message.toCharArray();
				for (int i = 0; i < charArray.length - 1; i++) {
					if ((charArray[i] == '"' || charArray[i] == '\'') && !gotError && !gotWord) {
						gotError = true;
					} else if ((charArray[i] != '"' && charArray[i] != '\'') && gotError && !gotWord) {
						actualWord.append(charArray[i]);
					} else if ((charArray[i] == '"' || charArray[i] == '\'') && gotError && !gotWord) {
						String[] extactWords = actualWord.toString().split("\\W+");

						for (String eachWord : messages) {
							for (String extact : extactWords) {
								if (extact.equalsIgnoreCase(eachWord)) {
									incorrectWords.add(actualWord.toString());
									gotWord = true;
								}
							}

						}
						if (!gotWord) {
							actualWord = new StringBuilder();
						}
						gotError = false;

					}
				}

				List<Replacement> replacements = match.getReplacements();
				Replacement replacement = null;
				if (replacements.size() > 0) {
					replacement = replacements.get(replacements.size() - 1);
				}
				// offset and there replacement

				correctedWordMap.put(match.getRule().getId(),
						Explain.builder().originalWord(actualWord.toString())
								.replacedWord(replacement != null ? replacement.getValue() : null)
								.explaination(match.getMessage()).build());

			}

		}
		Map<Integer, Explain> filterOffsetReplaceWord = filterOffsetReplaceWord(text, correctedWordMap);
	*/

	}

	private Map<Integer, Explain> filterOffsetReplaceWord(String text, Map<String, Explain> explainMap) {
		Map<Integer, Explain> positioningMap = new HashMap<Integer, Explain>();
		String[] originalWords = text.split(" ");
		Map<String, Integer> originWordMap = new HashMap<String, Integer>();
		int positionOfword = 0;
		for (String origin : originalWords) {
			originWordMap.put(origin, positionOfword);
			positionOfword = positionOfword + 1;
		}

		Collection<Explain> explains = explainMap.values();

		for (Explain explain : explains) {
			String eachOrginWord = explain.getOriginalWord();
			String replacedWord = explain.getReplacedWord();

			if (!StringUtils.isEmpty(eachOrginWord) && (originWordMap.get(eachOrginWord) != null
					|| (originWordMap.get(eachOrginWord.toLowerCase()) != null
							|| originWordMap.get(eachOrginWord.toUpperCase()) != null))) {
				Integer offset = getExactWordFromReplaceMap(eachOrginWord, originWordMap);
				positioningMap.put(offset, explain);
			} else if (!StringUtils.isEmpty(eachOrginWord) && originWordMap.get(eachOrginWord) == null) {
				// check for partial search
				Integer offset = getExactWordFromReplaceMap(replacedWord, originWordMap);
				positioningMap.put(offset, explain);	
			} else if (StringUtils.isEmpty(eachOrginWord)) {

				String[] words = replacedWord.split("\\W+");
				StringBuilder builderReplaceWord = new StringBuilder();

				for (String eachReplace : words) {
					builderReplaceWord.append(eachReplace);
				}
				if (originWordMap.get(builderReplaceWord.toString()) != null) {
					explain.setOriginalWord(builderReplaceWord.toString());
					Integer offset = originWordMap.get(builderReplaceWord.toString());
					positioningMap.put(offset, explain);
				} else {
					positioningMap.put(-1, explain);
				}

			}

		}

		return positioningMap;

	}

	public Integer getExactWordFromReplaceMap(String word, Map<String, Integer> originWordMap) {

		if (originWordMap.get(word) != null) {
			return originWordMap.get(word);
		} else if (originWordMap.get(word.toLowerCase()) != null) {
			return originWordMap.get(word.toLowerCase());
		} else if( originWordMap.get(word.toUpperCase())!=null) {
			return originWordMap.get(word.toUpperCase());
		}
		
		return -1;
		

	}

	private boolean isPartialMatch(String s1, String s2) {

		return false;
	}

	

}
