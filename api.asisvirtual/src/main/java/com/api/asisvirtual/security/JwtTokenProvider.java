package com.api.asisvirtual.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.api.asisvirtual.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	

	@Value("${jwt.secret.key}")
	private String jwtSecret;

	@Value("${jwt.time.expiration}")
	private String jwtExpirationMs;

    public String generateToken(UserDetails userDetails, Usuario user) {
    	  Map<String, Object> claims = new HashMap<>();
          claims.put("role", user.getRole());
        return Jwts.builder()
        		.setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(this.jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS256, this.jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}