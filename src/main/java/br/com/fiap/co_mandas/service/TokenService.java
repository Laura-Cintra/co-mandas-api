package br.com.fiap.co_mandas.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.co_mandas.model.Token;
import br.com.fiap.co_mandas.model.User;
import br.com.fiap.co_mandas.repository.UserRepository;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    private Instant expiresAt = LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret");

    public Token createToken(User user) {
        var jwt = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        // .withClaim("role", user.getAuthorities())

        return new Token(jwt, user.getEmail());
    }

    // public User getUserFromToken(String token){

    // // verificando se o token foi válido
    // var verifiedToken = JWT.require(algorithm).build().verify(token);

    // return User.builder()
    // .id(Long.valueOf(verifiedToken.getSubject()))
    // .email(verifiedToken.getClaim("email").toString())
    // .build();
    // }

    public User getUserFromToken(String token) {
        var verifiedToken = JWT.require(algorithm).build().verify(token);
        String email = verifiedToken.getClaim("email").asString();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}