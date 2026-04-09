package com.example.disasterrelief.controller;

import jakarta.validation.Valid;
import com.example.disasterrelief.dto.request.DistributionRequestDTO;
import com.example.disasterrelief.dto.response.DistributionResponseDTO;
import com.example.disasterrelief.service.DistributionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Distributions")
public class DistributionController {

    private final DistributionService distributionService;

    public DistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    // 1. GET: Fetch all distribution records
    @GetMapping("/getDistribution")
    public ResponseEntity<List<DistributionResponseDTO>> getDistributions() {
        List<DistributionResponseDTO> responseList = distributionService.getAllDistributions();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/getDistributionById/{id}")
    public ResponseEntity<DistributionResponseDTO> getDistributionById(@PathVariable Integer id) {
        DistributionResponseDTO response = distributionService.getDistributionById(id);
        return ResponseEntity.ok(response);
    }

    // 2. POST: Create a new record
    @PostMapping("/createDistribution")
    public ResponseEntity<DistributionResponseDTO> createDistribution(@RequestBody @Valid DistributionRequestDTO requestDto) {
        DistributionResponseDTO response = distributionService.saveDistribution(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 3. PUT: Update an existing record
    // Added /{id} to the path so you can specify which one to update in the URL
    @PutMapping("/updateDistribution/{id}")
    public ResponseEntity<DistributionResponseDTO> updateDistribution(@PathVariable Integer id, @RequestBody DistributionRequestDTO request) {
        // We force the ID from the URL into the DTO to ensure consistency
        request.setDistributionId(id);

        DistributionResponseDTO response = distributionService.updateDistribution(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDistribution/{id}")
    public ResponseEntity<String> deleteDistribution(@PathVariable Integer id) {
        distributionService.deleteDistribution(id);
        return ResponseEntity.ok("Record with ID " + id + " has been deleted successfully.");
    }
}



//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.dto.request.DistributionRequestDTO;
//import com.example.disasterrelief.dto.response.DistributionResponseDTO;
//import com.example.disasterrelief.service.DistributionService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Distributions")
//public class DistributionController {
//
//    private final DistributionService distributionService;
//
//    public DistributionController(DistributionService distributionService) {
//        this.distributionService = distributionService;
//    }
//
//    // 1. GET: Fetch all distribution records
//    @GetMapping
//    public ResponseEntity<List<DistributionResponseDTO>> getDistributions() {
//        List<DistributionResponseDTO> responseList = distributionService.getAllDistributions();
//        return new ResponseEntity<>(responseList, HttpStatus.OK);
//    }
//
//    // 2. POST: Create a new record
//    @PostMapping
//    public ResponseEntity<DistributionResponseDTO> createDistribution(@RequestBody DistributionRequestDTO requestDto) {
//        DistributionResponseDTO response = distributionService.saveDistribution(requestDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    // 3. PUT: Update an existing record
//    // Added /{id} to the path so you can specify which one to update in the URL
//    @PutMapping("/{id}")
//    public ResponseEntity<DistributionResponseDTO> update(@PathVariable Integer id, @RequestBody DistributionRequestDTO request) {
//        // We force the ID from the URL into the DTO to ensure consistency
//        request.setDistributionId(id);
//
//        DistributionResponseDTO response = distributionService.updateDistribution(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable Integer id) {
//        distributionService.deleteDistribution(id);
//        return ResponseEntity.ok("Record with ID " + id + " has been deleted successfully.");
//    }
//}