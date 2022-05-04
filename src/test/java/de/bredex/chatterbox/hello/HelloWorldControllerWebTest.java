package de.bredex.chatterbox.hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloWorldControllerWebTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldControllerWebTest.class);

	@LocalServerPort
	private int port;

	@Test
	void helloReturnsHelloWorld(@Autowired TestRestTemplate restTemplate) {
		LOGGER.info("This test is using server port {}", port);
		ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello World");
	}
	
	@Test
	void helloReturnsHelloName(@Autowired TestRestTemplate restTemplate) {
		final String name = "Superwoman";
		ResponseEntity<String> response = restTemplate.getForEntity("/hello/{name}", String.class, name);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello " + name);
	}

	@Test
	void helloReturnsProvidedGreetingAndName(@Autowired TestRestTemplate restTemplate) {
		final String name = "Superwoman";
		final String greeting = "Moin moin";
		ResponseEntity<String> response = restTemplate.getForEntity("/hello/{name}?greeting={greeting}", String.class,
				name, greeting);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(greeting + " " + name);
	}

	@Test
	@Disabled
	void sendMail(@Autowired TestRestTemplate restTemplate) {
		final String message = "Nice to meet you";
		ResponseEntity<String> response = restTemplate.postForEntity("/mail", new HttpEntity<String>(message),
				String.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(message);
	}

	@Test
	void sendMailWithMessage(@Autowired TestRestTemplate restTemplate) {
		final Mail mail = new Mail("Alf", "Hat jemand die Katze gesehen?");
		ResponseEntity<Mail> response = restTemplate.postForEntity("/mail", mail,
				Mail.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		final Mail receivedMessage = response.getBody();
		assertThat(receivedMessage.getMsg()).isEqualTo(mail.getMsg());
		assertThat(receivedMessage.getSender()).isEqualTo(receivedMessage.getSender());
	}

}
