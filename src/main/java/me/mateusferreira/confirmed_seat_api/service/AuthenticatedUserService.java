package me.mateusferreira.confirmed_seat_api.service;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.exception.UnauthorizedAccessException;
import me.mateusferreira.confirmed_seat_api.model.entity.User;
import me.mateusferreira.confirmed_seat_api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public Long getAuthenticatedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return Long.parseLong(jwt.getSubject());
    }

    public User get(){
        return userRepository.findById(getAuthenticatedUserId())
                .orElseThrow(UnauthorizedAccessException::new);
    }
}
