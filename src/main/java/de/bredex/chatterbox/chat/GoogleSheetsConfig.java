package de.bredex.chatterbox.chat;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

@Configuration
@EnableConfigurationProperties(GoogleSheetsRoomsProperties.class)
public class GoogleSheetsConfig {

//	@Value("${google.sheets.rooms.apiKey}")
//	private String apiKey;

	@Autowired
	private GoogleSheetsRoomsProperties properties;

	public GoogleSheetsConfig(@Autowired GoogleSheetsRoomsProperties properties) {
		super();
		this.properties = properties;
	}

	@Bean
	public Sheets roomSheets() throws GeneralSecurityException, IOException {
		NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		GoogleClientRequestInitializer KEY_INITIALIZER = CommonGoogleClientRequestInitializer.newBuilder()
				.setKey(properties.getApiKey()).build();
		return new Sheets.Builder(HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), null)
				.setGoogleClientRequestInitializer(KEY_INITIALIZER).build();
	}
	
}
