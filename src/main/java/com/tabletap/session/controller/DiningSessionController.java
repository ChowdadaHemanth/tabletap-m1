package com.tabletap.session.controller;

import com.tabletap.session.dto.ScanResponse;
import com.tabletap.session.service.DiningSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiningSessionController {

    private final DiningSessionService diningSessionService;

    @GetMapping("/scan")
    public ScanResponse scanTable(@RequestParam("table") String qrCode) {
        return diningSessionService.scanTable(qrCode);
    }
}