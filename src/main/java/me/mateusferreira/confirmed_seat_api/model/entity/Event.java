package me.mateusferreira.confirmed_seat_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private OffsetDateTime eventDate;
    private Integer capacity;
    private String location;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private Boolean isActive = true;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "event")
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();
}
