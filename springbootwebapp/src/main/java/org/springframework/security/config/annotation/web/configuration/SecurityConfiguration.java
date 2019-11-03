package org.springframework.security.config.annotation.web.configuration;
/*
 * http://appsdeveloperblog.com/user-authentication-spring-boot-spring-security-jwt/
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import guru.springframework.filter.AuthenticationFilter;
import guru.springframework.services.UsersService;
import guru.springframework.domain.*;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 private final UsersService usersService;
/*
    private final UsersService usersService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
*/    
	 private final PasswordEncoder passwordEncoder;
    @Autowired
    public SecurityConfiguration(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
        
        http
        .authorizeRequests().antMatchers("/console/**").permitAll().and()
        .authorizeRequests().antMatchers("/pruducts/**").permitAll().and()
        .authorizeRequests().antMatchers("/users/**").permitAll().and()
        .addFilter(getAuthenticationFilter()).antMatcher("/**").authenticationProvider(getAuthenticationProvider());

         
    }

	    
private AuthenticationProvider getAuthenticationProvider() {
		// TODO Auto-generated method stub
		return null;
	}

/*
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.cors().and().csrf().disable();
         
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                 .addFilter(getAuthenticationFilter())
        		  .addFilter(getAuthenticationFilter());
        		  
        getAuthenticationProvider
        http.headers().frameOptions().disable();
        
        
    }


        */
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);
    }
    
    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),usersService );
        return filter;
    }	

}