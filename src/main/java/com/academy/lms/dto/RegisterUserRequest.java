package com.academy.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO для запроса регистрации нового пользователя.
 */
public record RegisterUserRequest(

        @NotBlank(message = "Email обязателен")
        @Email(message = "Некорректный формат email")
        @Size(max = 255, message = "Email слишком длинный")
        String email,

        @NotBlank(message = "Пароль обязателен")
        @Size(min = 8, max = 100, message = "Пароль должен быть от 8 до 100 символов")
        String password
) {
}