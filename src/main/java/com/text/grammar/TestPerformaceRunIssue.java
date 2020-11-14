package com.text.grammar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/socket")
public class TestPerformaceRunIssue {

	
	
	/*
	 * OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
RequestBody body = RequestBody.create(mediaType, "language=en-US&text=i%20will%20go%20to%20bed");
Request request = new Request.Builder()
	.url("https://grammarbot.p.rapidapi.com/check")
	.post(body)

	.build();

Response response = client.newCall(request).execute();
	 * 
	 */
	

	
	@GetMapping("/testRun")
	public String getPerformanceTest() throws Exception {
		//String url ="http://localhost:8082/boot/testRestBuilder";
		//HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		
		long a=System.currentTimeMillis();
		System.out.println(a);

		Thread.sleep(3000);
		long b=System.currentTimeMillis();
		System.out.println(b-a);
		System.out.println("it will print after 5 sec ");
		String s="got";
		return s; //+template.exchange(url, HttpMethod.GET,entity,  String.class).getBody();	
	}
	
	
	
		
}




