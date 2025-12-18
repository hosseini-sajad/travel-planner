package com.xone.travelplanner.controller;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.AuthenticationResponse;
import com.xone.travelplanner.dto.UserLoginDTO;
import com.xone.travelplanner.dto.UserRegisterDTO;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.security.JwtUtils;
import com.xone.travelplanner.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @Valid @RequestBody UserRegisterDTO userDto
    ) throws TravelException {
        User user = modelMapper.map(userDto, User.class);

        userService.register(user);

        String token = jwtUtils.generateTokenFromId(user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthenticationResponse.builder()
                        .token(token)
                        .user(user)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @Valid @RequestBody UserLoginDTO loginDto
    ) throws TravelException {
        User user = userService.login(loginDto.getEmail(), loginDto.getPassword());

        String token = jwtUtils.generateTokenFromId(user.getId());

        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(token)
                .user(user)
                .build();

        return ResponseEntity.ok(response);
    }
}
