package me.mateusferreira.confirmed_seat_api.repository;

import me.mateusferreira.confirmed_seat_api.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
