package com.tabletap.table.service;

import com.tabletap.table.dto.CreateTableRequest;
import com.tabletap.table.dto.TableResponse;
import com.tabletap.table.dto.UpdateTableRequest;

import java.util.List;

public interface RestaurantTableService {

    TableResponse createTable(CreateTableRequest request);

    List<TableResponse> getAllTables();

    TableResponse getTableById(Long id);

    TableResponse updateTable(Long id, UpdateTableRequest request);

    void deleteTable(Long id);

}