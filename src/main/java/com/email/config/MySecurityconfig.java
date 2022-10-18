package com.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.email.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityconfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	CustomUserDetailService customUserDetails;
	
	@Autowired
	private JWTAuthfilter jwtfilter;
	
	@Autowired
	private JWTAuthenticationEntry entrypoint;
	
	//All information about urls
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.csrf().disable()
		.cors();
		
		http.authorizeRequests().antMatchers("/token")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().exceptionHandling().authenticationEntryPoint(entrypoint);
		
		
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	
	
	//authentication type
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserDetails);
	}
	
	
	@Bean
	public PasswordEncoder passwordencoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public AuthenticationManager authenticationmangerbean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	
	@Bean

    CorsConfigurationSource corsConfigurationSource() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
	}
}
