package com.example.photoappapi.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter{
	
	private Environment environment;
	@Autowired
	public WebSecurity(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security.csrf().disable();
		security.headers().frameOptions().disable();
		security.authorizeRequests().antMatchers("/**").permitAll();
		/*.antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
		.antMatchers(environment.getProperty("api.users.actuator.url.path")).permitAll()
		.antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("api.registration.url.path")).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("api.login.url.path")).permitAll().
		anyRequest().authenticated()
		.and().addFilter(new AuthorisationFilter(authenticationManager(), environment));*/
		
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
