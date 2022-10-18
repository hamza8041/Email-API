package com.email.model;

public class Response {
	
	String message;

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + "]";
	}
	
	

}
