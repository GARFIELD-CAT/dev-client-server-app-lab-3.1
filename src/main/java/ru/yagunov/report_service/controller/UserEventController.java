package ru.yagunov.report_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.yagunov.data.api.UserEventApi;
import ru.yagunov.data.model.UserEvent;
import ru.yagunov.report_service.repository.UserEventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserEventController implements UserEventApi {
    private final UserEventRepository userEventRepository;

    @Override
    public ResponseEntity<Object> eventsPost(List<UserEvent> events) {
        List<ru.yagunov.report_service.model.UserEvent> baseUserEvents = new ArrayList<>();

        for (UserEvent event : events) {
            ru.yagunov.report_service.model.UserEvent userEvent = new ru.yagunov.report_service.model.UserEvent();
            userEvent.setEventType(event.getEventType());
            userEvent.setEventTime(event.getEventTime());

            baseUserEvents.add(userEvent);
        }

        userEventRepository.saveAll(baseUserEvents);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
