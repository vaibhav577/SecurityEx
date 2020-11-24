package in.nit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("raja").password("{noop}raja").authorities("ADMIN");
		auth.inMemoryAuthentication().withUser("veer").password("{noop}veer").authorities("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("vaibh").password("{noop}vaibh").authorities("STUDENT");
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  
	    http.authorizeRequests().antMatchers("/home").permitAll()
	    .antMatchers("/emp").hasAuthority("EMPLOYEE")
	    .antMatchers("/st").hasAuthority("STUDENT")
	    .and()
	    .formLogin()
	    .defaultSuccessUrl("/home")
	    
	    .and()
	    .logout()
	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    
	    .and()
	    .exceptionHandling()
	    .accessDeniedPage("/exc");
	    
	}
	
	
	
	
	
}
