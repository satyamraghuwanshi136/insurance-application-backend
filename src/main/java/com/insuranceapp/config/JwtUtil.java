package com.insuranceapp.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	//@Value("${app.secret.key}")
		private String SECRET_KEY = "examportal";
		
		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
		
		//code to get expiration date
		public Date extractExpirationDate(String token) {
			return extractClaim(token, Claims::getExpiration);
		}
		
		public <T> T extractClaim(String token, Function<Claims, T>claimsResolver) {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}

		private Claims extractAllClaims(String token) {
			// TODO Auto-generated method stub
			return Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token)
					.getBody();
		}
		
		// code to check if token is expired
			public boolean isTokenExpired(String token) {
				return extractExpirationDate(token).before(new Date());
			}
			
		// code to generate Token
		public String generateToken(UserDetails userDetails) {
			Map<String, Object> claims = new HashMap<>();
			return createToken(claims, userDetails.getUsername());
		}

		// code to create Token
			public String createToken(Map<String, Object> claims, String subject) {
				String tokenId= String.valueOf(new Random().nextInt(10000));
				return Jwts.builder()
						.setClaims(claims)
						.setSubject(subject)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
						.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
						.compact();
			}

		
		
		// code to check if token is valid as per username
			public boolean isValidToken(String token, UserDetails userDetails) {
				final String username = extractUsername(token);
				return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
			}
}
