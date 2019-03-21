package com.neptune.restservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.neptune.restservice.security.JwtTokenFilterConfigurer;
import com.neptune.restservice.security.JwtTokenProvider;
import com.neptune.restservice.utility.NeptuneConstant;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Disable CSRF (cross site request forgery)
		http.csrf().disable();
		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()//
        .antMatchers("/admin/token").permitAll()//
        .antMatchers("/actuator/**").hasAuthority(NeptuneConstant.PERM_MANAGEMENT_API)
        // Disallow everything else..
        .anyRequest().authenticated();
       // Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Override
	  public void configure(WebSecurity web) throws Exception {
	    // Allow swagger to be accessed without authentication
	    web.ignoring().antMatchers("/v2/api-docs")//
	        .antMatchers("/swagger-resources/**")//
	        .antMatchers("/actuator/info")//
	        .antMatchers("/actuator/health")//
	        .antMatchers("/swagger-ui.html")//
	        .antMatchers("/configuration/**")//
	        .antMatchers("/webjars/**")//
	        .antMatchers("/public");;
	  }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}