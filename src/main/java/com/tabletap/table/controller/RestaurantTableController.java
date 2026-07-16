package com.tabletap.table.controller;

import com.tabletap.table.dto.CreateTableRequest;
import com.tabletap.table.dto.TableResponse;
import com.tabletap.table.dto.UpdateTableRequest;
import com.tabletap.table.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TableResponse createTable(@Valid @RequestBody CreateTableRequest request) {
        return restaurantTableService.createTable(request);
    }

    @GetMapping
    public List<TableResponse> getAllTables() {
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/{id}")
    public TableResponse getTableById(@PathVariable Long id) {
        return restaurantTableService.getTableById(id);
    }

    @PutMapping("/{id}")
    public TableResponse updateTable(@PathVariable Long id,
                                     @Valid @RequestBody UpdateTableRequest request) {
        return restaurantTableService.updateTable(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable Long id) {
        restaurantTableService.deleteTable(id);
    }
}