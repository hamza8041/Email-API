package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.Email;
import com.email.model.Response;
import com.email.services.EmailService;

@RestController
@CrossOrigin(origins = "*")

public class EmailController {
	
	@Autowired
	private EmailService ES;
	
	@RequestMapping("/welcome")
	public String Welcome()
	{
		return "Hello this is my email. Not allowed for Unauthenticated users";
	}
	
	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public ResponseEntity<?> sendemail( @RequestBody Email email)
	{
		
		boolean res=this.ES.sendemail(email.getSubj(),email.getMessage(),email.getTo(),email);
		if(res)
		{
			return ResponseEntity.ok(new Response("Email is sent"));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Server error"));
			
		}
		
	}
	

}
