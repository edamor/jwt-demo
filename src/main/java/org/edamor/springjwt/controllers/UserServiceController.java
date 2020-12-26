package org.edamor.springjwt.controllers;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.edamor.springjwt.models.User;
import org.edamor.springjwt.services.AuthService;
import org.edamor.springjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserServiceController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Operation(summary = "Register new user")
    @ApiResponse(responseCode = "200", description = "Successfully added user")
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @Operation(summary = "Authenticate user credentials")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Authenticated",
        content = { @Content(mediaType = "text/plain;charset=UTF-8")})
    })
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return authService.authorizeUser(user);
    }


    @Operation(summary = "Verify your token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Decoded",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Claims.class))})
    })
    @PostMapping("/parse-jwt")
    public Jws<Claims> parseJwt(@RequestBody String token) {
        return authService.parseJwt(token);
    }
}
