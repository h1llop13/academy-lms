package com.academy.lms.service;

import com.academy.lms.dto.RegisterUserRequest;
import com.academy.lms.dto.RegisterUserResponse;
import com.academy.lms.user.User;
import com.academy.lms.user.UserRepository;
import com.academy.lms.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterUserResponse registerUser (RegisterUserRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalStateException("Пользователь с таким email уже существует");
        }

        // хешируем пароль
        String hashedPassword = passwordEncoder.encode(request.password());

        // создаем сущность User
        User user = User.builder()
                .email(request.email().toLowerCase()) // Сохраняем в нижнем регистре
                .passwordHash(hashedPassword)
                .role(UserRole.STUDENT) // По умолчанию все новые пользователи — студенты
                .build();

        // сохраняем в БД
        User savedUser = userRepository.save(user);

        // возвращаем ответ (без пароля)
        return new RegisterUserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );

    }
}
