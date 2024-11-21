package co.simplon.socwork.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@Profile("!prod")
public class SecurityConfig {
    @Value("${co.simplon.socwork.cors}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("POST");
	    }
	};
    }

    @Value("${co.simplon.socwork.rounds}")
    private int rounds;

    @Bean
    public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(rounds);
    }

    @Value("${co.simplon.socwork.secret}")
    private String secret;

    @Bean
    JwtProvider jwtProvider() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(algorithm);
    }
}
