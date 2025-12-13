package com.xone.travelplanner.dto;

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
public class UserResponseDTO {
    private String token;
    private User user;
    private String message;
    private Integer code;
    private Map<String, Object> additionalInfo;

    public static UserResponseDTO success(String token, User user) {
        return UserResponseDTO.builder()
                .token(token)
                .user(user)
                .build();
    }

    public static UserResponseDTO error(Integer code, String errorMessage) {
        UserResponseDTO response = new UserResponseDTO();
        response.setMessage(errorMessage);
        response.setCode(code);
//        if (response.getAdditionalInfo() == null) {
//            response.setAdditionalInfo(new HashMap<>());
//        }
//        response.getAdditionalInfo().put("error", errorMessage);
        return response;
    }
}
