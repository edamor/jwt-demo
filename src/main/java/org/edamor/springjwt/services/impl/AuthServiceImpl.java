package org.edamor.springjwt.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.edamor.springjwt.models.User;
import org.edamor.springjwt.repositories.UserRepository;
import org.edamor.springjwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Override
    public String authorizeUser(User user) {
        User foundUser = userRepo.findByUsername(user.getUsername());
        if (foundUser == null) {
            return "INVALID USERNAME";
        }
        if (BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
            Claims claims = Jwts.claims()
                    .setSubject(foundUser.getUsername())
                    .setIssuer("JWT Demo")
                    .setIssuedAt(new Date());
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(secretKey)
                    .claim("userId", foundUser.getId())
                    .claim("password", foundUser.getPassword())
                    .compact();
        } return "INCORRECT PASSWORD";
    }

    @Override
    public Jws<Claims> parseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
