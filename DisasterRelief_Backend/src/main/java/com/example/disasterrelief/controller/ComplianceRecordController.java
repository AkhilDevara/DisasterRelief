package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import com.example.disasterrelief.dto.request.ComplianceRecordRequestDTO;
import com.example.disasterrelief.dto.response.ComplianceRecordResponseDTO;
import com.example.disasterrelief.service.ComplianceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-records")
public class ComplianceRecordController {

    @Autowired
    private ComplianceRecordService service;

    @PostMapping("/createComplianceRecord")
    public ResponseEntity<ComplianceRecordResponseDTO> create(@Valid @RequestBody ComplianceRecordRequestDTO request) {
        return ResponseEntity.ok(service.createRecord(request));
    }

    @GetMapping("/getAllComplianceRecord")
    public ResponseEntity<List<ComplianceRecordResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRecords());
    }

    @PutMapping("/updateComplianceRecord/{id}")
    public ResponseEntity<ComplianceRecordResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ComplianceRecordRequestDTO request) {
        return ResponseEntity.ok(service.updateRecord(id, request));
    }
}


//package com.example.disasterrelief.controller;
//
//import jakarta.validation.Valid;
//import com.example.disasterrelief.dto.request.ComplianceRecordRequestDTO;
//import com.example.disasterrelief.dto.response.ComplianceRecordResponseDTO;
//import com.example.disasterrelief.service.ComplianceRecordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/compliance-records")
//public class ComplianceRecordController {
//
//    @Autowired
//    private ComplianceRecordService service;
//
//    @PostMapping
//    public ResponseEntity<ComplianceRecordResponseDTO> create(@Valid @RequestBody ComplianceRecordRequestDTO request) {
//        return ResponseEntity.ok(service.createRecord(request));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ComplianceRecordResponseDTO>> getAll() {
//        return ResponseEntity.ok(service.getAllRecords());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ComplianceRecordResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ComplianceRecordRequestDTO request) {
//        return ResponseEntity.ok(service.updateRecord(id, request));
//    }
//}