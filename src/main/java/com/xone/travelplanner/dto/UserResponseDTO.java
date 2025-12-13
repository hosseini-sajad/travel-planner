package com.xone.travelplanner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xone.travelplanner.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private String token;
    private User user;
    private Integer code;
    private String message;
    private Map<String, Object> additionalInfo;

    public static UserResponseDTO success(String token, User user) {
        return UserResponseDTO.builder()
                .token(token)
                .user(user)
                .build();
    }

    public static UserResponseDTO error(Integer code, String errorMessage) {
        return UserResponseDTO.builder()
                .code(code)
                .message(errorMessage)
                .build();
    }
}
