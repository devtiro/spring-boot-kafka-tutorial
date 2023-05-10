package com.devtiro.kafkatutorial;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.devtiro.kafkatutorial.config.KafkaConfigProps;
import com.devtiro.kafkatutorial.domain.CustomerVisitEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class KafkaTutorialApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaTutorialApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate, final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
		final CustomerVisitEvent event = CustomerVisitEvent.builder()
			.customerId(UUID.randomUUID().toString())
			.dateTime(LocalDateTime.now())
			.build();

			final String payload = objectMapper.writeValueAsString(event);

			return args -> {
				kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
			};
	}

	@KafkaListener(topics = "customer.visit")
	@Profile("!test")
	public String listens(final String in) {
		System.out.println(in);
		return in;
	}

}
