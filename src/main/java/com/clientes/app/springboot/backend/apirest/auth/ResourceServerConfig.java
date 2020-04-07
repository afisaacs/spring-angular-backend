package com.clientes.app.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET , "/api/cliente", "/api/cliente/page/**", "/api/upload/img/**", "/images/**").permitAll()
		
		/**
		//Autorización clientes
		.antMatchers(HttpMethod.GET , "/api/cliente", "/api/cliente/page/**", "/upload/img/**").permitAll()
		.antMatchers(HttpMethod.POST , "/api/cliente/upload").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST , "/api/cliente").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT , "/api/cliente").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE , "/api/cliente").hasRole("ADMIN")
		.antMatchers("/api/cliente/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET , "/api/region/**").permitAll()
		
		// Autorización regiones
		.antMatchers(HttpMethod.GET , "/api/region").permitAll()
		
		// Defecto
		 **/
		.anyRequest().authenticated()
		.and()
		.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
