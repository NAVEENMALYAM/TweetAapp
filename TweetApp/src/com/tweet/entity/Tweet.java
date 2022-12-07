package com.tweet.entity;

import java.time.LocalDateTime;

public class Tweet {

	private String email;
	private String message;
	private String date;
	
	public Tweet() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    public String getDate() {
		return date;
	}

	public void setDate( String  date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Tweet [email=" + email + ", message=" + message + ", date=" + date + "]";
	}
	
}
