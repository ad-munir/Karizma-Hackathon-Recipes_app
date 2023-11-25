package com.recette.app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "Username is required!")
    private String username;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 8, message = "Password must contains at least 8 characters")
    @Size(max = 20, message = "Password must not exceed 20 characters")
    private String password;

}