package de.bredex.chatterbox.hello;

public class Mail {

	private String msg;

	private String sender;

	private Mail() {
	}

	public Mail(String sender, String msg) {
		this();
		this.msg = msg;
		this.sender = sender;
	}

	public String getMsg() {
		return msg;
	}

	public String getSender() {
		return sender;
	}

}