package com.example.test.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//import com.example.test.config.ServiceKeyConfig;
import com.example.test.dto.ChatResponse;




@Service
public class QuestionAnswerService {
	// @Value("${serviceKey}")
	// private String openaiApiKey;
	
	// @Value("${serviceKey}")
	// private String key;

	public ChatResponse getAnswer(String query) {
		String serviceKey = "sha256:8b30556db4ceb7343dc543a072a32e50e58431d81c854ad38df630d6dfbb9f6c";
		//String serviceKey = key;
		String authorization = null;
		//build AI server uri 
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8000")
				.path("/chat")
				.queryParam("query", query)
				.queryParam("serviceKey", serviceKey)
				.queryParam("Authorization", authorization)
				.encode()
				.build()
				.toUri();
		// var headers = new HttpHeaders();
		// headers.set("Authorization", authorization);
		// headers.setContentType(MediaType.APPLICATION_JSON);
		
		//System.out.println(uri.toString());
		
		// //Rest template 생성해서 위에서 build한 uri로 response 받아오
		RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
		ResponseEntity<ChatResponse> result = restTemplate.getForEntity(uri, ChatResponse.class);
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		// var httpEntity = new HttpEntity<>(headers);
		// var responseType = new ParameterizedTypeReference<ChatResponse>(){};
		// var responseEntity = new RestTemplate().exchange(
		// 	uri, 
		// 	HttpMethod.GET,
		// 	httpEntity,
		// 	responseType
		// );
		
		return result.getBody();
	}

}
