//
//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.entity.AuditLog;
//import com.example.disasterrelief.service.AuditLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/logs")
//public class AuditLogController {
//
//    @Autowired
//    private AuditLogService auditLogService;
//
//    @GetMapping("/GetAllLogs")
//    public List<AuditLog> getAllLogs() {
//        return auditLogService.getAllLogs();
//    }
//
//    @PostMapping("/CreateLog")
//    public AuditLog createLog(@RequestBody AuditLog log) {
//        // This now matches the method signature in the Service
//        return auditLogService.logAction(log);
//    }
//}


package com.example.disasterrelief.controller;

import com.example.disasterrelief.dto.request.AuditLogRequestDTO;
import com.example.disasterrelief.entity.AuditLog;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.service.AuditLogService;
import com.example.disasterrelief.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private UserService userService;

    // ✅ GET ALL LOGS
    @GetMapping("/GetAllLogs")
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }

    // ✅ CREATE LOG WITH VALIDATION
    @PostMapping("/CreateLog")
    public AuditLog createLog(
            @Valid @RequestBody AuditLogRequestDTO requestDTO) {

        // Load User using userId from DTO
        User user = userService.getUserById(requestDTO.getUserId());

        // Map DTO → Entity
        AuditLog log = new AuditLog();
        log.setUser(user);
        log.setAction(requestDTO.getAction());
        log.setResource(requestDTO.getResource());
        log.setTimestamp(requestDTO.getTimestamp());
        log.setIpAddress(requestDTO.getIpAddress());
        log.setDetails(requestDTO.getDetails());

        // Save using service
        return auditLogService.logAction(log);
    }
}

