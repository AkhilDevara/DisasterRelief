package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import com.example.disasterrelief.dto.request.IncidentRequestDTO;
import com.example.disasterrelief.dto.request.AssignOfficerRequestDTO;
import com.example.disasterrelief.dto.request.IncidentStatusUpdateRequestDTO;
import com.example.disasterrelief.dto.response.IncidentResponseDTO;
import com.example.disasterrelief.service.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    // CREATE INCIDENT
    @PostMapping("/createincident")
    public IncidentResponseDTO createIncident(@RequestBody IncidentRequestDTO requestDTO) {
        return service.createIncident(requestDTO);
    }

    // GET ALL INCIDENTS
    @GetMapping("/getallincident")
    public List<IncidentResponseDTO> getAllIncidents() {
        return service.getAllIncidents();
    }

    // GET INCIDENT BY ID
    @GetMapping("/getincidentbyid/{id}")
    public IncidentResponseDTO getIncidentById(@PathVariable @Valid int id) {
        return service.getIncidentById(id);
    }

    // UPDATE INCIDENT STATUS
    @PutMapping("updateincident/{id}/status")
    public IncidentResponseDTO updateStatus(
            @PathVariable int id,
            @RequestBody IncidentStatusUpdateRequestDTO statusRequest) {

        return service.updateIncidentStatus(id, statusRequest.getStatus());
    }

    // ASSIGN OFFICER TO INCIDENT
    @PutMapping("/assignofficer/{id}/assign-officer")
    public IncidentResponseDTO assignOfficer(
            @PathVariable int id,
            @RequestBody AssignOfficerRequestDTO requestDTO) {

        return service.assignOfficer(id, requestDTO.getOfficerId());
    }
    @DeleteMapping("/delete/{id}")
    public String deleteIncident(@PathVariable @Valid int id) {
        return service.deleteIncident(id);
    }
}



//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.dto.request.IncidentRequestDTO;
//import com.example.disasterrelief.dto.request.AssignOfficerRequestDTO;
//import com.example.disasterrelief.dto.request.IncidentStatusUpdateRequestDTO;
//import com.example.disasterrelief.dto.response.IncidentResponseDTO;
//import com.example.disasterrelief.service.IncidentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/incidents")
//public class IncidentController {
//
//    private final IncidentService service;
//
//    public IncidentController(IncidentService service) {
//        this.service = service;
//    }
//
//    // CREATE INCIDENT
//    @PostMapping
//    public IncidentResponseDTO createIncident(@RequestBody IncidentRequestDTO requestDTO) {
//        return service.createIncident(requestDTO);
//    }
//
//    // GET ALL INCIDENTS
//    @GetMapping
//    public List<IncidentResponseDTO> getAllIncidents() {
//        return service.getAllIncidents();
//    }
//
//    // GET INCIDENT BY ID
//    @GetMapping("/{id}")
//    public IncidentResponseDTO getIncidentById(@PathVariable int id) {
//        return service.getIncidentById(id);
//    }
//
//    // UPDATE INCIDENT STATUS
//    @PutMapping("/{id}/status")
//    public IncidentResponseDTO updateStatus(
//            @PathVariable int id,
//            @RequestBody IncidentStatusUpdateRequestDTO statusRequest) {
//
//        return service.updateIncidentStatus(id, statusRequest.getStatus());
//    }
//
//    // ASSIGN OFFICER TO INCIDENT
//    @PutMapping("/{id}/assign-officer")
//    public IncidentResponseDTO assignOfficer(
//            @PathVariable int id,
//            @RequestBody AssignOfficerRequestDTO requestDTO) {
//
//        return service.assignOfficer(id, requestDTO.getOfficerId());
//    }
//}