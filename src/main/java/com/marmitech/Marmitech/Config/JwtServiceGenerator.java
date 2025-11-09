package com.marmitech.Marmitech.Config;

//JwtService.java

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import java.nio.charset.StandardCharsets;

import com.marmitech.Marmitech.Entity.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceGenerator {

	///////////////////////////////////////////////////////

	public static final String SECRET_KEY = "TWFybWl0ZWNoU3VwZXJDaGF2ZVNlY3JldGFKV1QyMDI1XyNAJCEl1";
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

	public Map<String, Object> gerarPayload(Usuario usuarioService) {

		Map<String, Object> payloadData = new HashMap<>();
		payloadData.put("nome", usuarioService.getUsername());
		payloadData.put("id", usuarioService.getId());
//		payloadData.put("admin",usuarioService.getSenha());
        payloadData.put("email", usuarioService.getEmail());
		payloadData.put("senha", usuarioService.getPassword());
		return payloadData;
	}






	public String generateToken(Usuario usuarioService) {

		Map<String, Object> payloadData = this.gerarPayload(usuarioService);

		return Jwts
				.builder()
				.setClaims(payloadData)
				.setSubject(usuarioService.getNome())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(new Date().getTime() + 3600000 * this.HORAS_EXPIRACAO_TOKEN))
				.signWith(getSigningKey(), this.ALGORITMO_ASSINATURA)
				.compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}


	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}


	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

}
