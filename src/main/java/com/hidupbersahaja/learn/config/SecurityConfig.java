package com.hidupbersahaja.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.hidupbersahaja.learn.security.AuthenticationSuccessHandler;
import com.hidupbersahaja.learn.security.RestAuthenticationEntryPoint;

@ComponentScan("${component.scan}")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		
		String user1 = "staff";
		String pass1 = passwordEncorder().encode("staff");
		
		
		String user2 = "admin";
		String pass2 = passwordEncorder().encode("admin");
		
        auth.inMemoryAuthentication()
          .withUser(user1).password(pass1).roles("STAFF")
          .and()
          .withUser(user2).password(pass2).roles("ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/api/**").authenticated()
        .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout();
    }
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncorder(){
		return new BCryptPasswordEncoder();
	}
	
}
