package com.ledshra.Challenge_Foro_Hub.infra.security;

import com.ledshra.Challenge_Foro_Hub.domain.usuario.Usuario;
import com.ledshra.Challenge_Foro_Hub.domain.usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(usuario.getPassword());
            return JWT.create()
                    .withIssuer("api")
                    .withSubject(usuario.getUsername())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(expirationdate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }
    public String getSubject(String token){
        if (token == null) {
            throw new IllegalArgumentException("El token es nulo.");
        }
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            if (username == null) {
                throw new IllegalArgumentException("Token inválido");
            }
            Usuario usuario = (Usuario) usuarioRepository.findByEmail(username);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado: " + username);
            }

            Algorithm algorithm = Algorithm.HMAC256(usuario.getPassword());
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("api")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token inválido: " + e.getMessage(), e);
        }
    }
    private Instant expirationdate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
