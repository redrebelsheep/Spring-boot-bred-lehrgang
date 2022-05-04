package de.bredex.chatterbox.chat;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.client.util.Lists;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

@Repository
public class RoomRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomRepository.class);

//	@Value("${google.sheets.rooms.sheetId}")
//	private String sheetId;
//
//	@Value("${google.sheets.rooms.sheet}")
//	private String sheet;
//
//	@Value("${google.sheets.rooms.majorDimension}")
//	private String majorDimension;

	private Sheets roomSheet;

	private GoogleSheetsRoomsProperties properties;

	public RoomRepository(@Autowired Sheets roomSheets, @Autowired GoogleSheetsRoomsProperties properties) {
		super();
		this.roomSheet = roomSheets;
		this.properties = properties;
	}

	public List<String> findAll() {
		List<String> rooms = Lists.newArrayList();
		try {
			ValueRange result = roomSheet.spreadsheets().values().get(properties.getSheetId(), properties.getSheet())
					.setMajorDimension(properties.getMajorDimension()).execute();
			List<Object> values = result.getValues().get(0);
			rooms = values.stream().map(Object::toString).collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error("Something went wrong when I tried to retrieve the rooms from Google Sheets", e);
		}
		return rooms;
	}
	
	public boolean contains(String room) {
		return findAll().contains(room);
	}

}
