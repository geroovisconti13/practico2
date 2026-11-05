package ar.com.practico2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			@Qualifier("userDetailsService") UserDetailsService userDetailsService) {

		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(encoder());

		return new ProviderManager(provider);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login").permitAll()
						.requestMatchers("/global/**", "/static/**").permitAll()
						.requestMatchers("/roles", "/roles/**", "/usuarios", "/usuarios/**", "/api/**").hasRole("Administrador")
						.requestMatchers("/").authenticated()
						.anyRequest().authenticated()
				)

				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/", true)
						.permitAll()
				)

				.logout(logout -> logout
						.permitAll()
				)

				.httpBasic(httpBasic -> {});

		return http.build();
	}
}