package com.tabletap.table.dto;

import com.tabletap.table.entity.TableStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TableResponse {

    private Long id;

    private String tableNumber;

    private String qrCode;

    private Integer seats;

    private TableStatus status;
}