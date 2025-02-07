package loja.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import java.util.Date;

@Service
public class JwtUtils {

    @Value("${jwt.expire}")
    private String expiration;

    private final SecretKey key;


    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generate(String userId, String role, String tokenType){
        long expireToken = "ACCESS".equalsIgnoreCase(tokenType) ? Long.parseLong(expiration) * 1000 : Long.parseLong(expiration) * 1000 * 5;
        var expirationDate = new Date(System.currentTimeMillis() + expireToken);

        return Jwts.builder()
                .claim("role", role)
                .subject(userId)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
}
