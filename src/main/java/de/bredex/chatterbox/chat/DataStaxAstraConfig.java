package de.bredex.chatterbox.chat;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class DataStaxAstraConfig {

	@Autowired
	private DataStaxAstraProperties properties;

	public DataStaxAstraConfig(@Autowired DataStaxAstraProperties properties) {
		super();
		this.properties = properties;
	}

	@Bean
	public CqlSession cqlSession() {
		return CqlSession.builder().withKeyspace(properties.getKeyspaceName())
				.withCloudSecureConnectBundle(Paths.get(properties.getSecureConnectPackage()))
				.withAuthCredentials(properties.getClientId(), properties.getClientSecret()).build();
	}

}