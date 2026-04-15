package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.mapper.UserMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreatedResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;
import me.mateusferreira.confirmed_seat_api.repository.UserRepository;
import me.mateusferreira.confirmed_seat_api.service.validator.RoleValidator;
import me.mateusferreira.confirmed_seat_api.service.validator.UserRegisterValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisterValidator userRegisterValidator;

    @Transactional
    public UserCreatedResponse register(UserCreateRequest request) {

        RoleValidator.validate(request.role());
        userRegisterValidator.validate(request);

        User user = UserMapper.toEntity(request);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }
}
