package me.mateusferreira.confirmed_seat_api.controller;

import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserProfileResponse;
import me.mateusferreira.confirmed_seat_api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfileResponse me(){
        return userService.getMe();
    }
}
