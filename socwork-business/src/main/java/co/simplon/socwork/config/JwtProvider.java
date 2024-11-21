package co.simplon.socwork.config;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtProvider {
    private final Algorithm algorithm;

    JwtProvider(Algorithm algorithm) {
	this.algorithm = algorithm;
    }

    public String create(String subject) {
	Instant issuedAt = Instant.now();
	Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject);
	return builder.sign(algorithm);
    }

}
