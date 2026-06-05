package ch.noseryoung.restaurantbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtService {

    private final Key secretKey;
    private final long expirationTimeMs;

    public JwtService(@Value("${app.security.jwt-secret}") String base64Secret, @Value("${app.security.jwt-expiration-ms:86400000}") long expirationTimeMs) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTimeMs = expirationTimeMs;
    }

    public String generateToken(String username, Iterable<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", extractPrimaryRole(authorities));

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs)).signWith(secretKey).compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    public boolean isTokenValid(String token) {
        try {
            return !extractAllClaims(token).getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public long getExpirationTimeMs() {
        return expirationTimeMs;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private String extractPrimaryRole(Iterable<? extends GrantedAuthority> authorities) {
        Optional<String> role = Optional.empty();
        for (GrantedAuthority authority : authorities) {
            role = Optional.ofNullable(authority.getAuthority());
            if (role.isPresent()) {
                break;
            }
        }
        return role.orElse("ROLE_USER");
    }
}
