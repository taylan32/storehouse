package com.example.storehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.example.storehouse.utils.security.JwtAuthenticationEntryPoint;
import com.example.storehouse.utils.security.JwtAuthenticationFilter;
import com.example.storehouse.utils.security.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImp userDetailsService;

	private JwtAuthenticationEntryPoint handler;

	public SecurityConfig(UserDetailsServiceImp userDetailsService, JwtAuthenticationEntryPoint handler) {
		this.userDetailsService = userDetailsService;
		this.handler = handler;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(handler);

		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.authorizeRequests().antMatchers("/api/auth/**").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/api/users/**").hasAnyAuthority("admin","manager");
		httpSecurity.authorizeRequests().antMatchers("/api/roles/**").hasAnyAuthority("admin", "manager");
		httpSecurity.authorizeRequests().antMatchers("/api/departments/**").hasAnyAuthority("admin","manager");
		httpSecurity.authorizeRequests().antMatchers("/api/suppliers/**").hasAnyAuthority("admin","manager");
		httpSecurity.authorizeRequests().antMatchers("/api/rawmaterials/**").hasAnyAuthority("admin","manager");
		httpSecurity.authorizeRequests().antMatchers("/api/orders/**").hasAnyAuthority("admin","manager");
		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		//httpSecurity.authorizeRequests().antMatchers("/api/roles/**").authenticated();
	
		
		/*
		 * httpSecurity .cors() .and() .csrf().disable()
		 * .exceptionHandling().authenticationEntryPoint(handler).and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		 * and() .authorizeRequests() .antMatchers(HttpMethod.GET, "/roles")
		 * .permitAll() .antMatchers("/api/users/**") .permitAll()
		 * .antMatchers("/api/auth/**") .permitAll() .anyRequest().authenticated();
		 */

		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}
}