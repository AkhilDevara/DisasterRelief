package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import com.example.disasterrelief.dto.request.AuditRequestDTO;
import com.example.disasterrelief.dto.response.AuditResponseDTO;
import com.example.disasterrelief.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping
    public ResponseEntity<AuditResponseDTO> create(@Valid @RequestBody AuditRequestDTO request) {
        return ResponseEntity.ok(auditService.createAudit(request));
    }

    @GetMapping
    public ResponseEntity<List<AuditResponseDTO>> getAll() {
        return ResponseEntity.ok(auditService.getAllAudits());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody AuditRequestDTO request) {
        return ResponseEntity.ok(auditService.updateAudit(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        auditService.deleteAudit(id);
        return ResponseEntity.ok("Audit deleted successfully");
    }
}

//
//package org.cognizant.disastermanagement.controller;
//
//import jakarta.validation.Valid;
//import org.cognizant.disastermanagement.dto.request.AuditRequestDTO;
//import org.cognizant.disastermanagement.dto.response.AuditResponseDTO;
//import org.cognizant.disastermanagement.service.AuditService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//
//@RestController
//@RequestMapping("/api/audits")
//public class AuditController {
//
//    @Autowired
//    private AuditService auditService;
//
//    @PostMapping("/createaudit")
//    public ResponseEntity<AuditResponseDTO> create(@Valid @RequestBody AuditRequestDTO request) {
//        return ResponseEntity.ok(auditService.createAudit(request));
//    }
//
//    @GetMapping("/getAllAudit")
//    public ResponseEntity<List<AuditResponseDTO>> getAll() {
//        return ResponseEntity.ok(auditService.getAllAudits());
//    }
//
//    @PutMapping("/updateAuditById/{id}")
//    public ResponseEntity<AuditResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody AuditRequestDTO request) {
//        return ResponseEntity.ok(auditService.updateAudit(id, request));
//    }
//
//    @DeleteMapping("/deleteAuditById/{id}")
//    public ResponseEntity<String> delete(@PathVariable Integer id) {
//        auditService.deleteAudit(id);
//        return ResponseEntity.ok("Audit deleted successfully");
//    }
//}