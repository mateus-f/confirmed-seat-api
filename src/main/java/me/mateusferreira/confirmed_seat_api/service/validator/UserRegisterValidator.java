package me.mateusferreira.confirmed_seat_api.service.validator;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.exception.BusinessException;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterValidator {

    private final UserRepository userRepository;

    public void validate(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email inválido ou indisponível.");
        }

        if (userRepository.existsByCpf(request.cpf())) {
            throw new BusinessException("CPF inválido ou indisponível.");
        }
    }
}
