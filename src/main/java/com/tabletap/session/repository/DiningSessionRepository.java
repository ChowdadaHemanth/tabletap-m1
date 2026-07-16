package com.tabletap.session.repository;

import com.tabletap.session.entity.DiningSession;
import com.tabletap.session.entity.SessionStatus;
import com.tabletap.table.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiningSessionRepository extends JpaRepository<DiningSession, Long> {

    Optional<DiningSession> findByTableAndStatus(RestaurantTable table,
                                                 SessionStatus status);

}