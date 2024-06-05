package com.revature.revhire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://revhire-job-service:8000")
				.build();
	}
}
