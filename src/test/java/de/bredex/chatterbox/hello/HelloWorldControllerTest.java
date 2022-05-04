package de.bredex.chatterbox.hello;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorldControllerTest {

	@Autowired
	private HelloWorldController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void helloReturnsHelloWorld() {
		assertThat(controller.hello(Optional.empty(), Optional.empty())).isEqualTo("Hello World");
	}
	
}
