package de.bredex.chatterbox.chat;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("google.sheets.rooms")
public class GoogleSheetsRoomsProperties {

	private String apiKey;

	private String sheetId;

	private String sheet;

	private String majorDimension;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getMajorDimension() {
		return majorDimension;
	}

	public void setMajorDimension(String majorDimension) {
		this.majorDimension = majorDimension;
	}

}
