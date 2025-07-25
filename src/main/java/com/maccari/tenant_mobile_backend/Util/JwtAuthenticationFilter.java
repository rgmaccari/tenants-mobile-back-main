package com.maccari.tenant_mobile_backend.Util;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de segurança que intercepta as requisições HTTP para validar se o usuário está autenticado através de um token JWT.
 * A extensão de "OncePerRequestFilter" permite que o filtro seja aplicado apenas uma vez por requisição.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Método responsável pela validação do token recebido nas requisições. Utiliza da extração do token/usuário através do Header "Authorization" para criar uma autenticação do Spring Security.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                String username = jwtUtil.getUsernameFromToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
              }

            } catch (ExpiredJwtException e) {
                logger.warn("Token expirado: {}" + e.getMessage());
            } catch (MalformedJwtException e) {
                logger.warn("Token malformado: {}" + e.getMessage());
            } catch (JwtException e) {
                logger.error("Erro genérico no token JWT: {}" + e.getMessage());
            } catch (Exception e) {
                logger.error("Erro inesperado durante validação do token JWT. " + e);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/auth/login");
    }

}
