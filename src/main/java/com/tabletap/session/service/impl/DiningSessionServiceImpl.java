package com.tabletap.session.service.impl;

import com.tabletap.exception.ResourceNotFoundException;
import com.tabletap.session.dto.ScanResponse;
import com.tabletap.session.entity.DiningSession;
import com.tabletap.session.entity.SessionStatus;
import com.tabletap.session.repository.DiningSessionRepository;
import com.tabletap.session.service.DiningSessionService;
import com.tabletap.table.entity.RestaurantTable;
import com.tabletap.table.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DiningSessionServiceImpl implements DiningSessionService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final DiningSessionRepository diningSessionRepository;

    @Override
    public ScanResponse scanTable(String qrCode) {

        RestaurantTable table = restaurantTableRepository.findByQrCode(qrCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid QR Code"));

        DiningSession activeSession = diningSessionRepository
                .findByTableAndStatus(table, SessionStatus.ACTIVE)
                .orElse(null);

        if (activeSession != null) {

            return ScanResponse.builder()
                    .sessionId(activeSession.getId())
                    .tableNumber(table.getTableNumber())
                    .message("Active session already exists")
                    .build();
        }

        DiningSession session = DiningSession.builder()
                .table(table)
                .status(SessionStatus.ACTIVE)
                .startedAt(LocalDateTime.now())
                .build();

        DiningSession savedSession = diningSessionRepository.save(session);

        return ScanResponse.builder()
                .sessionId(savedSession.getId())
                .tableNumber(table.getTableNumber())
                .message("Dining session started successfully")
                .build();
    }
}