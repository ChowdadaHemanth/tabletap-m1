package com.tabletap.table.repository;

import com.tabletap.table.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    Optional<RestaurantTable> findByTableNumber(String tableNumber);

    Optional<RestaurantTable> findByQrCode(String qrCode);

    boolean existsByTableNumber(String tableNumber);

    boolean existsByQrCode(String qrCode);

}