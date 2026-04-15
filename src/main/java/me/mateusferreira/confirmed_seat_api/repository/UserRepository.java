package me.mateusferreira.confirmed_seat_api.repository;

import me.mateusferreira.confirmed_seat_api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
