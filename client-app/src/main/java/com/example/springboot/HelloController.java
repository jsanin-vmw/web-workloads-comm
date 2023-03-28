package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

	final RestTemplate restTemplate;

	@Value("${endpoints.server-app-url}")
	private String serverAppUrl;

	public HelloController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/")
	public String index() {
		return "Greetings from client-app + Tanzu!";
	}

	@RequestMapping("/calling-server-app")
	public String callServerApp() {
		ResponseEntity<String> response = restTemplate.getForEntity(serverAppUrl, String.class);
		return "The server says: " + response.getBody();
	}

}