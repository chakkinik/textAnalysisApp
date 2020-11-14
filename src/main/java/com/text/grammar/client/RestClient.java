package com.text.grammar.client;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.text.grammar.model.GrammarCorrectionResponse;


@Component
public class RestClient {
	

	RestTemplate template;

	@Autowired
	RestTemplateBuilder restBuilder;

	@PostConstruct
	public void init() {
		template = restBuilder
				.build();

	}
	
	
	public GrammarCorrectionResponse getApiResponse(String url ,String text) throws Exception {
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

		body.add("text", text);
		body.add("langauge", "en-US");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body,
				getGrammarBotHeaders());
		ResponseEntity<GrammarCorrectionResponse> exchange = template.exchange(url, HttpMethod.POST, request,
				GrammarCorrectionResponse.class);
		
		return exchange.getBody();
		
	}
	
	private static HttpHeaders getGrammarBotHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-rapidapi-host", "grammarbot.p.rapidapi.com");
		headers.add("x-rapidapi-key", "WMg43emT6Hmsh08ncmbVUtUDfDcJp18eaPpjsnP8f9BJMravev");
		headers.add("content-type", "application/x-www-form-urlencoded");

		return headers;
	}

}
