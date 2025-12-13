package com.xone.travelplanner.controller;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.UserLoginDTO;
import com.xone.travelplanner.dto.UserRegisterDTO;
import com.xone.travelplanner.dto.UserResponseDTO;
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
    public ResponseEntity<UserResponseDTO> registerUser(
            @Valid @RequestBody UserRegisterDTO userDto) {
        try {
            // Convert DTO to Entity
            User user = modelMapper.map(userDto, User.class);
            
            // Create the user
            userService.register(user);
            
            // Generate JWT token
            String token = jwtUtils.generateTokenFromEmail(user);
            
            // Create and return success response
            return ResponseEntity.ok(
                UserResponseDTO.success(
                    token, 
                    user
                )
            );
        } catch (TravelException e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(UserResponseDTO.error(e.getCode(), e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(
            @Valid @RequestBody UserLoginDTO loginDto) {
        try {
            // Authenticate user and update first login status if needed
            User user = userService.login(loginDto.getEmail(), loginDto.getPassword());
            
            // Generate JWT token
            String token = jwtUtils.generateTokenFromEmail(user);
            
            // Create and return success response
            UserResponseDTO response = UserResponseDTO.success(token, user);
            return ResponseEntity.ok(response);
            
        } catch (TravelException e) {
            UserResponseDTO errorResponse = UserResponseDTO.error(e.getCode(), e.getMessage());
            errorResponse.setCode(errorResponse.getCode());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        } catch (Exception e) {
            UserResponseDTO errorResponse = UserResponseDTO.error(500, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
