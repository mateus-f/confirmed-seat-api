package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.mapper.UserCreateMapper;
import me.mateusferreira.confirmed_seat_api.mapper.UserLoginMapper;
import me.mateusferreira.confirmed_seat_api.mapper.UserSummaryMapper;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserLoginRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserLoginResponse;
import me.mateusferreira.confirmed_seat_api.model.entity.User;
import me.mateusferreira.confirmed_seat_api.repository.UserRepository;
import me.mateusferreira.confirmed_seat_api.service.validator.RoleValidator;
import me.mateusferreira.confirmed_seat_api.service.validator.UserRegisterValidator;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRegisterValidator userRegisterValidator;


    @Transactional
    public UserCreateResponse register(UserCreateRequest request) {

        RoleValidator.validate(request.role());
        userRegisterValidator.validate(request);

        User user = UserCreateMapper.toEntity(request);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return UserCreateMapper.toResponse(user);
    }

    public UserLoginResponse login(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("E-mail e/ou senha inválidos."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("E-mail e/ou senha inválidos.");
        }

        Instant now = Instant.now();
        Instant expirationTime = now.plus(2, HOURS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("confirmed-seat-api")
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .build();

        String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        long expiresIn = Duration.between(now, expirationTime).getSeconds();

        return UserLoginMapper.toResponse(jwtToken, expiresIn, UserSummaryMapper.toResponse(user));
    }
}
