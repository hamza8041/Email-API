package com.email.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.email.helper.JWTUtil;
import com.email.services.CustomUserDetailService;

@Component
public class JWTAuthfilter extends OncePerRequestFilter {
	
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private CustomUserDetailService cds;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get jwt
		//Bearer start
		//validate
		
		
		String authorization = request.getHeader("Authorization");
		
		String userName=null;
		String jwttoken=null;
		
		if(authorization!=null && authorization.startsWith("Bearer "))
		{
			jwttoken=authorization.substring(7);
			
			try
			{
				 userName = this.jwtutil.extractUsername(jwttoken);
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			UserDetails userdetails = this.cds.loadUserByUsername(userName);
		
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			
			}
			
			else
			{
				System.out.println("Username is null");
			}
			
			
			
			
		}
		
		filterChain.doFilter(request, response);

		
	}

}
