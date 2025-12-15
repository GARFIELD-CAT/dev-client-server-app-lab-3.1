package ru.yagunov.report_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private Long id;

    private String eventType;

    private OffsetDateTime eventTime;

    private LocalDate eventDate = LocalDate.now();
}