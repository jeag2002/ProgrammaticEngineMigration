package es.rtbserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
public class RTBServerConfig extends WebSecurityConfigurerAdapter{
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {       
	     http.csrf().disable();
	     http.httpBasic().and().authorizeRequests()
	     .antMatchers(HttpMethod.GET, "/**").permitAll()
	     .anyRequest().authenticated();
	 }
	

}
