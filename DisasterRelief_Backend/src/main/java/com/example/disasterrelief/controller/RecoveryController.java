package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.example.disasterrelief.dto.request.RecoveryProgramRequestDTO;
import com.example.disasterrelief.dto.response.RecoveryProgramResponseDTO;
import com.example.disasterrelief.dto.response.ResourceResponseDTO;
import com.example.disasterrelief.entity.RecoveryProgram;
import com.example.disasterrelief.service.RecoveryService;
import com.example.disasterrelief.Enum.RecoveryStatus;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/programs")
@AllArgsConstructor
public class RecoveryController {

    private final RecoveryService recoveryService;
    //private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<RecoveryProgramResponseDTO> createProgram(@Valid @RequestBody RecoveryProgramRequestDTO dto) {
        RecoveryProgram entity = toEntity(dto);//modelMapper.map(dto, RecoveryProgram.class);;
        recoveryService.createProgram(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(entity));
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<RecoveryProgramResponseDTO>> getAllPrograms() {
        List<RecoveryProgramResponseDTO> list = recoveryService.getAllPrograms()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /**
     * NEW: Fetch a single program by its ID
     * Endpoint: GET /api/programs/view/{id}
     */
    @GetMapping("/view/{id}")
    public ResponseEntity<RecoveryProgramResponseDTO> getProgramById(@PathVariable int id) {
        // Fetch from service (which throws exception if not found)
        RecoveryProgram program = recoveryService.getProgramById(id);
        // Map to Response DTO
        return ResponseEntity.ok(toResponseDTO(program));
    }

    // --- MAPPING METHODS ---

    private RecoveryProgramResponseDTO toResponseDTO(RecoveryProgram entity) {
        RecoveryProgramResponseDTO res = new RecoveryProgramResponseDTO();
        res.setProgramId(entity.getProgramId());
        res.setTitle(entity.getTitle());
        res.setDescription(entity.getDescription());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setBudget(entity.getBudget());
        res.setStatus(entity.getStatus());

        if (entity.getResources() != null) {
            res.setResources(entity.getResources().stream().map(resEntity -> {
                ResourceResponseDTO rDto = new ResourceResponseDTO();
                rDto.setResourceId(resEntity.getResourceId());
                rDto.setName(resEntity.getName());
                rDto.setType(resEntity.getType());
                rDto.setQuantity(resEntity.getQuantity());
                rDto.setUnit(resEntity.getUnit());
                rDto.setStatus(resEntity.getStatus());
                rDto.setReceivedBy(resEntity.getReceivedBy());
                return rDto;
            }).collect(Collectors.toList()));
        }
        return res;
    }

    private RecoveryProgram toEntity(RecoveryProgramRequestDTO dto) {
        RecoveryProgram entity = new RecoveryProgram();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setBudget(dto.getBudget());
        entity.setStatus(RecoveryStatus.PLANNED);
        return entity;
    }
}


//package com.example.disasterrelief.controller;
//
//import jakarta.validation.Valid;
//import com.example.disasterrelief.dto.request.RecoveryProgramRequestDTO;
//import com.example.disasterrelief.dto.response.RecoveryProgramResponseDTO;
//import com.example.disasterrelief.dto.response.ResourceResponseDTO;
//import com.example.disasterrelief.entity.RecoveryProgram;
//import com.example.disasterrelief.service.RecoveryService;
//import com.example.disasterrelief.Enum.RecoveryStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/programs")
//public class RecoveryController {
//
//    @Autowired
//    private RecoveryService recoveryService;
//
//    @PostMapping("/create")
//    public ResponseEntity<RecoveryProgramResponseDTO> createProgram(@Valid @RequestBody RecoveryProgramRequestDTO dto) {
//        RecoveryProgram entity = toEntity(dto);
//        recoveryService.createProgram(entity);
//        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(entity));
//    }
//
//    @GetMapping("/viewAll")
//    public ResponseEntity<List<RecoveryProgramResponseDTO>> getAllPrograms() {
//        List<RecoveryProgramResponseDTO> list = recoveryService.getAllPrograms()
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(list);
//    }
//
//    /**
//     * NEW: Fetch a single program by its ID
//     * Endpoint: GET /api/programs/view/{id}
//     */
//    @GetMapping("/view/{id}")
//    public ResponseEntity<RecoveryProgramResponseDTO> getProgramById(@PathVariable int id) {
//        // Fetch from service (which throws exception if not found)
//        RecoveryProgram program = recoveryService.getProgramById(id);
//        // Map to Response DTO
//        return ResponseEntity.ok(toResponseDTO(program));
//    }
//
//    // --- MAPPING METHODS ---
//
//    private RecoveryProgramResponseDTO toResponseDTO(RecoveryProgram entity) {
//        RecoveryProgramResponseDTO res = new RecoveryProgramResponseDTO();
//        res.setProgramId(entity.getProgramId());
//        res.setTitle(entity.getTitle());
//        res.setDescription(entity.getDescription());
//        res.setStartDate(entity.getStartDate());
//        res.setEndDate(entity.getEndDate());
//        res.setBudget(entity.getBudget());
//        res.setStatus(entity.getStatus());
//
//        if (entity.getResources() != null) {
//            res.setResources(entity.getResources().stream().map(resEntity -> {
//                ResourceResponseDTO rDto = new ResourceResponseDTO();
//                rDto.setResourceId(resEntity.getResourceId());
//                rDto.setName(resEntity.getName());
//                rDto.setType(resEntity.getType());
//                rDto.setQuantity(resEntity.getQuantity());
//                rDto.setUnit(resEntity.getUnit());
//                rDto.setStatus(resEntity.getStatus());
//                rDto.setReceivedBy(resEntity.getReceivedBy());
//                return rDto;
//            }).collect(Collectors.toList()));
//        }
//        return res;
//    }
//
//    private RecoveryProgram toEntity(RecoveryProgramRequestDTO dto) {
//        RecoveryProgram entity = new RecoveryProgram();
//        entity.setTitle(dto.getTitle());
//        entity.setDescription(dto.getDescription());
//        entity.setStartDate(dto.getStartDate());
//        entity.setEndDate(dto.getEndDate());
//        entity.setBudget(dto.getBudget());
//        entity.setStatus(RecoveryStatus.PLANNED);
//        return entity;
//    }
//}