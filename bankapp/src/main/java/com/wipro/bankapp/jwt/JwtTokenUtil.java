package com.wipro.bankapp.jwt;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username)  {
        KeyPair keyPair = generateKeyPair();
        Key privateKey = keyPair.getPrivate();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public List<String> extractRoles(String token) {
        List<?> roles = extractClaim(token, claims -> claims.get("roles", List.class));
        List<String> stringRoles = roles.stream().map(Object::toString).collect(Collectors.toList());
        return stringRoles;
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private boolean isTokenExpired(String token) {
    	
    	 Date expirationDate = extractExpirationDate(token);
         boolean isExpired=  expirationDate.before(new Date());
    	
			/*
			 * SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
			 * 
			 * Claims claims = Jwts.parserBuilder() .setSigningKey(key) .build()
			 * .parseClaimsJws(token) .getBody();
			 * 
			 * // Check if the token is expired isExpired =
			 * claims.getExpiration().before(Date.from(Instant.now()));
			 */
           if (isExpired) {
               System.out.println("######### Token has expired.");
           } else {
               System.out.println("######### Token is still valid.");
           }
           return isExpired;
       }
    
    
    
    private static KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048); // Key size of 2048 bits
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
    }
}

