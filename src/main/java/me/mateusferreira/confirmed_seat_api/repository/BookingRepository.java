package me.mateusferreira.confirmed_seat_api.repository;

import me.mateusferreira.confirmed_seat_api.model.entity.Booking;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByEventIdAndUserId(Long eventId, Long userId);

    Page<Booking> findAllByUserIdAndStatusNot(Long id, BookingStatus bookingStatus, Pageable pageable);

    Optional<Booking> findByConfirmationCode(UUID confirmationCode);

    Optional<Booking> findByConfirmationCodeAndUserId(UUID confirmationCode, Long userId);

}
