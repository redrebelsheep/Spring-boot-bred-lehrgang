package de.bredex.chatterbox.chat;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/*
 * use chat;
 * describe tables;
 * create table chat (room text, posted timestamp, sender text, message text, PRIMARY KEY (room, posted, sender)) WITH CLUSTERING ORDER BY (posted ASC, sender DESC);
 */
@Table
public class Chat {

	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String room;
	
	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
	private Instant posted; 
	
	@PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private String sender;
	
	private String message;

	public Chat(String room, Instant posted, String sender) {
		super();
		this.room = room;
		this.posted = posted;
		this.sender = sender;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Instant getPosted() {
		return posted;
	}

	public void setPosted(Instant posted) {
		this.posted = posted;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
