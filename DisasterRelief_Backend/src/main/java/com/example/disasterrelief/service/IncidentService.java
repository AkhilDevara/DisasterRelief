package com.example.disasterrelief.service;

import com.example.disasterrelief.dao.EmergencyRepository;
import com.example.disasterrelief.dao.IncidentRepository;
import com.example.disasterrelief.dao.UserRepository;
import com.example.disasterrelief.dto.request.IncidentRequestDTO;
import com.example.disasterrelief.dto.request.IncidentStatusUpdateRequestDTO;
import com.example.disasterrelief.dto.response.IncidentResponseDTO;
import com.example.disasterrelief.entity.EmergencyReport;
import com.example.disasterrelief.entity.Incident;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.Enum.IncidentStatus;
import com.example.disasterrelief.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepo;
    private final EmergencyRepository reportRepo;
    private final UserRepository userRepo;

    public IncidentService(IncidentRepository incidentRepo,
                           EmergencyRepository reportRepo,
                           UserRepository userRepo) {
        this.incidentRepo = incidentRepo;
        this.reportRepo = reportRepo;
        this.userRepo = userRepo;
    }

    // CREATE INCIDENT
    public IncidentResponseDTO createIncident(IncidentRequestDTO req) {

        EmergencyReport report = reportRepo.findById(req.getReportId())
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with ID: " + req.getReportId()));

        User officer = userRepo.findById(req.getOfficerId())
                .orElseThrow(() -> new ResourceNotFoundException("Officer not found with ID: " + req.getOfficerId()));

        Incident incident = new Incident();
        incident.setEmergencyReport(report);
        incident.setOfficer(officer);
        incident.setActions(req.getActions());
        incident.setStatus(req.getStatus());

        Incident saved = incidentRepo.save(incident);

        return toResponseDTO(saved);
    }

    // GET ALL INCIDENTS
    public List<IncidentResponseDTO> getAllIncidents() {
        return incidentRepo.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET INCIDENT BY ID
    public IncidentResponseDTO getIncidentById(int id) {
        Incident incident = incidentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with ID: " + id));
        return toResponseDTO(incident);
    }

    // UPDATE STATUS
    public IncidentResponseDTO updateIncidentStatus(int id, String status) {

        Incident incident = incidentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with ID: " + id));

        incident.setStatus(IncidentStatus.valueOf(status.toUpperCase()));

        Incident updated = incidentRepo.save(incident);
        return toResponseDTO(updated);
    }

    // ASSIGN OFFICER
    public IncidentResponseDTO assignOfficer(int id, int officerId) {

        Incident incident = incidentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with ID: " + id));

        User officer = userRepo.findById(officerId)
                .orElseThrow(() -> new ResourceNotFoundException("Officer not found with ID: " + officerId));

        incident.setOfficer(officer);

        Incident updated = incidentRepo.save(incident);
        return toResponseDTO(updated);
    }

    // DELETE INCIDENT
    public String deleteIncident(int id) {
        Incident incident = incidentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with ID: " + id));

        incidentRepo.delete(incident);
        return "Incident deleted successfully with ID: " + id;
    }

    // ENTITY → RESPONSE DTO
    private IncidentResponseDTO toResponseDTO(Incident entity) {

        IncidentResponseDTO dto = new IncidentResponseDTO();

        dto.setIncidentId(entity.getIncidentId());
        dto.setReportId(entity.getEmergencyReport().getReportId());
        dto.setOfficerId(entity.getOfficer().getUserId());
        dto.setActions(entity.getActions());
        dto.setStatus(entity.getStatus());
        dto.setDate(entity.getDate());

        return dto;
    }
}

//
//@Service
//public class IncidentService {
//
//    private final IncidentRepository incidentRepo;
//    private final EmergencyRepository reportRepo;
//    private final UserRepository userRepo;
//
//    public IncidentService(IncidentRepository incidentRepo,
//                           EmergencyRepository reportRepo,
//                           UserRepository userRepo) {
//        this.incidentRepo = incidentRepo;
//        this.reportRepo = reportRepo;
//        this.userRepo = userRepo;
//    }
//
//
//    // --------------------------------------
//    // CREATE INCIDENT
//    // --------------------------------------
//    public IncidentResponseDTO createIncident(IncidentRequestDTO req) {
//
//        EmergencyReport report = reportRepo.findById(req.getReportId())
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Report not found with ID: " + req.getReportId()));
//
//        User officer = userRepo.findById(req.getOfficerId())
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Officer not found with ID: " + req.getOfficerId()));
//
//        Incident incident = new Incident();
//        incident.setEmergencyReport(report);
//        incident.setOfficer(officer);
//        incident.setActions(req.getActions());
//        incident.setStatus(req.getStatus());
//
//        Incident saved = incidentRepo.save(incident);
//
//        return toResponseDTO(saved);
//    }
//
//
//    // --------------------------------------
//    // GET ALL INCIDENTS
//    // --------------------------------------
//    public List<IncidentResponseDTO> getAllIncidents() {
//        return incidentRepo.findAll()
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//
//    // --------------------------------------
//    // GET INCIDENT BY ID
//    // --------------------------------------
//    public IncidentResponseDTO getIncidentById(int id) {
//        Incident incident = incidentRepo.findById(id)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Incident not found with ID: " + id));
//        return toResponseDTO(incident);
//    }
//
//
//    // --------------------------------------
//    // UPDATE INCIDENT STATUS
//    // --------------------------------------
//    public IncidentResponseDTO updateIncidentStatus(int id, String status) {
//
//        Incident incident = incidentRepo.findById(id)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Incident not found with ID: " + id));
//
//        incident.setStatus(IncidentStatus.valueOf(status.toUpperCase()));
//
//        Incident updated = incidentRepo.save(incident);
//
//        return toResponseDTO(updated);
//    }
//
//
//    // --------------------------------------
//    // ASSIGN OFFICER TO INCIDENT
//    // --------------------------------------
//    public IncidentResponseDTO assignOfficer(int id, int officerId) {
//
//        Incident incident = incidentRepo.findById(id)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Incident not found with ID: " + id));
//
//        User officer = userRepo.findById(officerId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Officer not found with ID: " + officerId));
//
//        incident.setOfficer(officer);
//
//        Incident updated = incidentRepo.save(incident);
//
//        return toResponseDTO(updated);
//    }
//
//
//    // --------------------------------------
//    // DELETE INCIDENT
//    // --------------------------------------
//    public String deleteIncident(int id) {
//
//        Incident incident = incidentRepo.findById(id)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Incident not found with ID: " + id));
//
//        incidentRepo.delete(incident);
//
//        return "Incident deleted successfully with ID: " + id;
//    }
//
//
//
//    // --------------------------------------
//    // ENTITY → RESPONSE DTO
//    // --------------------------------------
//    private IncidentResponseDTO toResponseDTO(Incident entity) {
//
//        IncidentResponseDTO dto = new IncidentResponseDTO();
//
//        dto.setIncidentId(entity.getIncidentId());
//        dto.setActions(entity.getActions());
//        dto.setDate(entity.getDate());
//        dto.setStatus(entity.getStatus());
//
//        if (entity.getEmergencyReport() != null) {
//            dto.setReportId(entity.getEmergencyReport().getReportId());
//        }
//
//        if (entity.getOfficer() != null) {
//            dto.setOfficerId(entity.getOfficer().getUserId());
//        }
//
//        return dto;
//    }
//}
