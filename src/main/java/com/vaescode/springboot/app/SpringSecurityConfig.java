package com.vaescode.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaescode.springboot.app.auth.handler.LoginSuccesHandler;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// public static BCryptPasswordEncoder
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSuccesHandler succesHandler;
	
	@Autowired
	private DataSource dataSource;

	

	/* ACL - Listas de control de acceso - peemisos para acceder a las vistas */
	/* http://localhost:8080/login */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar*").permitAll()
//				.antMatchers("/ver/**").hasAnyRole("USER")
				//.antMatchers("/uploads/**").hasAnyRole("USER")
				/*
				 * .antMatchers("/form/**").hasAnyRole("ADMIN")
				 * .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
				 * .antMatchers("/factura/**").hasAnyRole("ADMIN")
				 */
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.successHandler(succesHandler)
					.loginPage("/login")
					.permitAll() 
				.and()
				.logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/error_403");
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select  username, password, enabled from users where username =?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=? ");
		
		//◦insert into authorities (user_id, authority) values(1, 'ROLE_USER');
		
		/*
		 * PasswordEncoder encoder = passwordEncoder; UserBuilder users =
		 * User.builder().passwordEncoder(encoder::encode);
		 * 
		 * builder.inMemoryAuthentication().withUser(users.username("admin").password(
		 * "12345").roles("ADMIN", "USER"))
		 * .withUser(users.username("cesar").password("12345").roles("USER"));
		 */
		/*
		 * UserBuilder users = User.builder().passwordEncoder(password -> { return
		 * encoder.encode(password); });
		 */
	}

}
