package org.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//pour definir la maniere dont on va chercher les utilisateurs
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("1234").roles("ADMIN","USER");
//		auth.inMemoryAuthentication()
//			.withUser("user").password("1234").roles("USER"); 
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username AS principal, password AS credentials, active FROM users WHERE username=? ")
			.authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM users_roles "
					+ "INNER JOIN users ON users_roles.user_id=users.id WHERE users.username=? ")
			.rolePrefix("ROLE_")
			.passwordEncoder(new Md5PasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login");

		http.authorizeRequests().antMatchers("/bank-account/operations").hasRole("USER");  
		http.authorizeRequests().antMatchers("/bank-account/saveOperation", "/users").hasRole("ADMIN");
		
		http.exceptionHandling().accessDeniedPage("/403");
	}
}