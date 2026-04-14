package me.mateusferreira.confirmed_seat_api.service.validator;

import me.mateusferreira.confirmed_seat_api.exception.InvalidRoleException;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

import java.util.List;

import static me.mateusferreira.confirmed_seat_api.model.enums.UserRole.ADMIN;
import static me.mateusferreira.confirmed_seat_api.model.enums.UserRole.CUSTOMER;

public class RoleValidator {

    private static final List<UserRole> ALLOWED_ROLES = List.of(ADMIN, CUSTOMER);

    private RoleValidator() {
    }

    private static boolean isValid(UserRole role) {
        return role != null && ALLOWED_ROLES.contains(role);
    }

    public static void validate(UserRole role) {
        if (!isValid(role)) {
            throw new InvalidRoleException("Role não permitida: " + role);
        }
    }
}
