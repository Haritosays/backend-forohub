package com.haroldo.forohub.controllers;

import com.haroldo.forohub.controllers.dto.AuthCreateUserRequest;
import com.haroldo.forohub.controllers.dto.AuthLoginRequest;
import com.haroldo.forohub.controllers.dto.AuthResponse;
import com.haroldo.forohub.service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Controller for Topics")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login user",
            description = "Authenticate a user and retunr the autheticatio token",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication request with username and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthLoginRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description =  "Succeful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation =  AuthResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

}

