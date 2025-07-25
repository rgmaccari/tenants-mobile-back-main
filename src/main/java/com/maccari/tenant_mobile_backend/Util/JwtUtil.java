package com.maccari.tenant_mobile_backend.Util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Criação e leitura de tokens JWT, que são utilizados para identificar e
 * autenticar usuários na aplicação.
 */
@Component
public class JwtUtil {
    
    @Value("${JWT.SECRET}")
    private String secretKey;
    private static final long EXPIRATION_TIME_MS = 86400000;

    /**
     * Criação do token de autenticação
     * 
     * @param username
     * @return
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //Define o nome do usuário no token.
                .setIssuedAt(new Date()) //Data de criação do token.
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS)) //Data de expiração do token.
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) //Assina o token com a chave secreta usando o algoritmo HS256
                .compact(); //Gera o token em formato de String compacta.
    }


    /**
     * Extrai o username do token recebido.
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) //Usa a chave secreta para verificar o token.
                .build()
                .parseClaimsJws(token) //Descompacta o token e verifica se é valido.
                .getBody()
                .getSubject(); //Retorna o nome do usuário guardado no body.
    }

}