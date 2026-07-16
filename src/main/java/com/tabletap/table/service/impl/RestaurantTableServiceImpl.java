package com.tabletap.table.service.impl;

import com.tabletap.exception.DuplicateResourceException;
import com.tabletap.exception.ResourceNotFoundException;
import com.tabletap.table.dto.CreateTableRequest;
import com.tabletap.table.dto.TableResponse;
import com.tabletap.table.dto.UpdateTableRequest;
import com.tabletap.table.entity.RestaurantTable;
import com.tabletap.table.entity.TableStatus;
import com.tabletap.table.repository.RestaurantTableRepository;
import com.tabletap.table.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    @Override
    public TableResponse createTable(CreateTableRequest request) {

        if (restaurantTableRepository.existsByTableNumber(request.getTableNumber())) {
            throw new DuplicateResourceException("Table number already exists");
        }

        String qrCode = "QR-" + request.getTableNumber();

        if (restaurantTableRepository.existsByQrCode(qrCode)) {
            throw new DuplicateResourceException("QR Code already exists");
        }

        RestaurantTable table = RestaurantTable.builder()
                .tableNumber(request.getTableNumber())
                .qrCode(qrCode)
                .seats(request.getSeats())
                .status(TableStatus.AVAILABLE)
                .build();

        RestaurantTable savedTable = restaurantTableRepository.save(table);

        return mapToResponse(savedTable);
    }

    @Override
    public List<TableResponse> getAllTables() {

        return restaurantTableRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TableResponse getTableById(Long id) {

        RestaurantTable table = restaurantTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Table not found with id : " + id));

        return mapToResponse(table);
    }

    @Override
    public TableResponse updateTable(Long id, UpdateTableRequest request) {

        RestaurantTable table = restaurantTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Table not found with id : " + id));

        table.setSeats(request.getSeats());

        RestaurantTable updatedTable = restaurantTableRepository.save(table);

        return mapToResponse(updatedTable);
    }

    @Override
    public void deleteTable(Long id) {

        RestaurantTable table = restaurantTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Table not found with id : " + id));

        restaurantTableRepository.delete(table);
    }

    private TableResponse mapToResponse(RestaurantTable table) {

        return TableResponse.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .qrCode(table.getQrCode())
                .seats(table.getSeats())
                .status(table.getStatus())
                .build();
    }
}