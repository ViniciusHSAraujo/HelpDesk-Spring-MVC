package com.vhsadev.helpdesk.configurations;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery("select email, active, password from user where email = ?")
		.authoritiesByUsernameQuery("select usr.email, rl.name from user usr "
								  + "inner join user_role usrr on (usr.user_id = usrr.user_id) "
								  + "inner join role rl on (usrr.role_id = rl.role_id) "
								  + "where usr.email = ?;")
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/registration").permitAll()
				.antMatchers("/**").hasAnyAuthority("ADMIN", "USER").anyRequest().authenticated().and().csrf().disable()
				.formLogin().loginPage("/login").failureUrl("/login?errors=true").defaultSuccessUrl("/users")
				.usernameParameter("email").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.exceptionHandling().accessDeniedPage("/denied");
	}

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/videos/**",
				"/resources/**");
	}
}
