package com.tabletap.table.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTableRequest {

    @NotNull(message = "Seats is required")
    @Min(value = 1, message = "Seats must be greater than 0")
    private Integer seats;
}