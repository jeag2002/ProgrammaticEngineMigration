package es.rtbserver.config;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import io.undertow.UndertowOptions;



@Configuration
@EnableWebSecurity(debug = true)
public class RTBServerSecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {       
	     http.csrf().disable();
	     http.httpBasic().and().authorizeRequests()
	     .antMatchers(HttpMethod.GET, "/**").permitAll()
	     .anyRequest().authenticated();
	 }
	 

}
