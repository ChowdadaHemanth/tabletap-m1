package com.tabletap.session.service;

import com.tabletap.session.dto.ScanResponse;

public interface DiningSessionService {

    ScanResponse scanTable(String qrCode);

}