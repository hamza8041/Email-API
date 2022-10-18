package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.helper.JWTUtil;
import com.email.model.JWTRequest;
import com.email.model.JWTResponse;
import com.email.services.CustomUserDetailService;

@RestController
@CrossOrigin(origins = "*")
public class JWTController {
	
	@Autowired
	private CustomUserDetailService customuserdetailservice;
	
	@Autowired
	private JWTUtil jwtutil;
	
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@RequestMapping(value="/token",method = RequestMethod.POST)
	public ResponseEntity<?>generate(@RequestBody JWTRequest jwt) throws Exception
	{
		System.out.println(jwt);
		
		try
		{
			this.authmanager.authenticate(new UsernamePasswordAuthenticationToken(jwt.getUserName(), jwt.getPassword()));
			
		}
		
		
		catch(BadCredentialsException e)
		{
		e.printStackTrace();
		throw new Exception("Bad Credentials");
		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
			
		}
		
		
		
		UserDetails userdetails = this.customuserdetailservice.loadUserByUsername(jwt.getUserName());
		
		String token = this.jwtutil.generateToken(userdetails);
		
		System.out.println("Jwt token is" + token);
		
		//json format where key is token and its value is value
		
		return ResponseEntity.ok(new JWTResponse(token));
		
		
	}

}
