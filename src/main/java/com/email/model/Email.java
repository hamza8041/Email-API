package com.email.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name = "Email")
public class Email {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emailid;
	
	@Column(name = "valueto")
	@org.hibernate.validator.constraints.Email
	@javax.validation.constraints.Email
	
	private String to;
	
	@Size(min = 5,max=100)
	@Column(name = "valuemessage")
	private String message;
	
	@Column(name = "valuesubj")
	private String subj;

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(int emailid, @org.hibernate.validator.constraints.Email @javax.validation.constraints.Email String to,
			@Size(min = 5, max = 100) String message, String subj) {
		super();
		this.emailid = emailid;
		this.to = to;
		this.message = message;
		this.subj = subj;
	}

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	@Override
	public String toString() {
		return "Email [emailid=" + emailid + ", to=" + to + ", message=" + message + ", subj=" + subj + "]";
	}

	
	
	
	
	
	
}
