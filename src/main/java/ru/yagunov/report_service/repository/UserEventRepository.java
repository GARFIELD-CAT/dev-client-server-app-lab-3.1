package ru.yagunov.report_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yagunov.report_service.model.UserEvent;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}