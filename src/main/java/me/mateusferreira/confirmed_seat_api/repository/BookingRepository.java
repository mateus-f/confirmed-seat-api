package me.mateusferreira.confirmed_seat_api.repository;

import me.mateusferreira.confirmed_seat_api.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
