package de.bredex.chatterbox.chat;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("datastax.astra")
public class DataStaxAstraProperties {

	private String keyspaceName;

	private String secureConnectPackage;

	private String clientId;

	private String clientSecret;

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}

	public String getSecureConnectPackage() {
		return secureConnectPackage;
	}

	public void setSecureConnectPackage(String secureConnectPackage) {
		this.secureConnectPackage = secureConnectPackage;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}
