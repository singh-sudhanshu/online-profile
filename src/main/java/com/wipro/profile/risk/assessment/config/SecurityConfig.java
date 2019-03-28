package com.wipro.profile.risk.assessment.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	private final DataSource dataSource;
	
	@Autowired
	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	 
	 private static final String USER_QUERY = "select email, password, active from user where email=?";
	 
	 private static final String ROLE_QUERY = "select u.email, r.name from user u inner join users_roles ur on (u.id = ur.user_id) inner join role r on (ur.role_id = r.id) where u.email=?";

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/anonymous*").anonymous()	
	        .antMatchers("/h2/**").hasRole("USER")	        
	        .antMatchers("/investmentrecommendation/CreateAccount/**").permitAll()
            .antMatchers("/investmentrecommendation/login*").permitAll()            
            .anyRequest().authenticated()            
            .and().headers().frameOptions().sameOrigin()
	                .and()
	                    .formLogin()
	                        .loginPage("/investmentrecommendation/login").failureUrl("/investmentrecommendation/login?error=true")
	                        .loginProcessingUrl("/login")	                        
	                        .successHandler(successHandler())
	                        .usernameParameter("userName")
	                        .passwordParameter("password")	                            
	                .and()
	                    .logout()
	                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                        .logoutSuccessUrl("/")	                        
	                        .invalidateHttpSession(true)
	                        .clearAuthentication(true);
	    }
	    
	    @Bean
	    public AuthenticationSuccessHandler successHandler(){
	        return new CustomAuthenticationSuccessHandler();
	    }
	    
	    public PersistentTokenRepository tokenRepository() {
	    	JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	    	db.setDataSource(dataSource);
	    	return db;
	    	
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }	    

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
	    		auth.jdbcAuthentication().usersByUsernameQuery(USER_QUERY)
		    	.authoritiesByUsernameQuery(ROLE_QUERY)
		    	.dataSource(dataSource)
		    	.passwordEncoder(passwordEncoder());
	    }

}
