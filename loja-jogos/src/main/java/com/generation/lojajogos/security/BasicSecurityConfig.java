package com.generation.lojajogos.security;

//A Classe BasicSecurityConfig é utilizada para sobrescrever a configuração padrão da Spring Security. 
//A Classe BasicSecurityConfig possui 2 anotações:

//@Configuration: indica que a Classe é do tipo configuração, ou seja, define uma Classe como fonte de definições de Beans, 
//além de ser uma das anotações essenciais ao utilizar uma configuração baseada em Java.
//@EnableWebSecurity: habilita a segurança de forma Global (toda a aplicação) e sobrescreve os Métodos que irão redefinir 
//as regras de Segurança da sua aplicação.

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;

	@Configuration
	@EnableWebSecurity
	public class BasicSecurityConfig {

	    @Bean //Bean faz a instancia sem precisar instanciar como "new"
	    public PasswordEncoder passwordEncoder() { //passwordEncoder indica que a aplicação está baseada em criptografia
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	    
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().csrf().disable()
	            .cors();

	        http
	            .authorizeHttpRequests((auth) -> auth
	                .antMatchers("/usuarios/logar").permitAll()
	                .antMatchers("/usuarios/cadastrar").permitAll()
	                .antMatchers(HttpMethod.OPTIONS).permitAll()
	                .anyRequest().authenticated())
	            .httpBasic();

	        return http.build();

	    }
}
