package com.tabletap.session.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScanResponse {

    private Long sessionId;

    private String tableNumber;

    private String message;
}