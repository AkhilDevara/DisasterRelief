package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import com.example.disasterrelief.dto.request.EmergencyReportRequestDTO;
import com.example.disasterrelief.dto.response.EmergencyReportResponseDTO;
import com.example.disasterrelief.dto.response.EmergencyReportDetailsResponseDTO;
import com.example.disasterrelief.service.EmergencyReportService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/reports")
public class EmergencyReportController {

    private final EmergencyReportService service;

    public EmergencyReportController(EmergencyReportService service) {
        this.service = service;
    }

    // CREATE REPORT
    @PostMapping("/createreport")
    public EmergencyReportResponseDTO createReport(@RequestBody EmergencyReportRequestDTO requestDTO) {
        return service.createReport(requestDTO);
    }

    // GET ALL REPORTS
    @GetMapping("/getallreports")
    public List<EmergencyReportResponseDTO> getAllReports() {
        return service.getAllReports();
    }

    // GET REPORT BY ID
    @GetMapping("/getreportbyid/{id}")
    public EmergencyReportResponseDTO getReportById(@PathVariable @Valid int id) {
        return service.getReportById(id);
    }

    // GET REPORT WITH CITIZEN DETAILS
    @GetMapping("getreportwithcitizendetails/{id}/details")
    public EmergencyReportDetailsResponseDTO getReportWithCitizen(@PathVariable @Valid int id) {
        return service.getReportWithCitizen(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable @Valid int id) {
        return service.deleteReport(id);
    }

}


//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.dto.request.EmergencyReportRequestDTO;
//import com.example.disasterrelief.dto.response.EmergencyReportResponseDTO;
//import com.example.disasterrelief.dto.response.EmergencyReportDetailsResponseDTO;
//import com.example.disasterrelief.service.EmergencyReportService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/reports")
//public class EmergencyReportController {
//
//    private final EmergencyReportService service;
//
//    public EmergencyReportController(EmergencyReportService service) {
//        this.service = service;
//    }
//
//    // CREATE REPORT
//    @PostMapping
//    public EmergencyReportResponseDTO createReport(@RequestBody EmergencyReportRequestDTO requestDTO) {
//        return service.createReport(requestDTO);
//    }
//
//    // GET ALL REPORTS
//    @GetMapping
//    public List<EmergencyReportResponseDTO> getAllReports() {
//        return service.getAllReports();
//    }
//
//    // GET REPORT BY ID
//    @GetMapping("/{id}")
//    public EmergencyReportResponseDTO getReportById(@PathVariable int id) {
//        return service.getReportById(id);
//    }
//
//    // GET REPORT WITH CITIZEN DETAILS
//    @GetMapping("/{id}/details")
//    public EmergencyReportDetailsResponseDTO getReportWithCitizen(@PathVariable int id) {
//        return service.getReportWithCitizen(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteReport(@PathVariable int id) {
//        return service.deleteReport(id);
//    }
//
//}