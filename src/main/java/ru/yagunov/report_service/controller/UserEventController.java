package ru.yagunov.report_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import ru.yagunov.data.api.UserEventApi;
import ru.yagunov.data.model.UserEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserEventController implements UserEventApi {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<Object> eventsPost(List<UserEvent> events) {
        List<ru.yagunov.report_service.model.UserEvent> baseUserEvents = new ArrayList<>();

        for (UserEvent event : events) {
            ru.yagunov.report_service.model.UserEvent userEvent = new ru.yagunov.report_service.model.UserEvent();
            userEvent.setEventType(event.getEventType());
            userEvent.setEventTime(event.getEventTime());

            baseUserEvents.add(userEvent);
        }

//        userEventRepository.saveAll(baseUserEvents);

        String sql = "INSERT INTO analytics.user_events (event_type, event_time, event_date) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ru.yagunov.report_service.model.UserEvent userEvent = baseUserEvents.get(i);
                ps.setString(1, userEvent.getEventType());
                ps.setObject(2, userEvent.getEventTime()); // Используйте setTimestamp, если это необходимо
                ps.setObject(3, userEvent.getEventDate());
            }

            @Override
            public int getBatchSize() {
                return baseUserEvents.size();
            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
