package org.edamor.springjwt.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.edamor.springjwt.models.User;

public interface AuthService {

    String authorizeUser(User user);

    Jws<Claims> parseJwt(String token);
}
