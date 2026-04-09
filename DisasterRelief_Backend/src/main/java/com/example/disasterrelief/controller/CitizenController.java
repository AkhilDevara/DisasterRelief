//package com.example.disasterrelief.controller;
//
//import com.example.disasterrelief.dto.request.CitizenRequestDTO;
//import com.example.disasterrelief.entity.Citizen;
//import com.example.disasterrelief.service.CitizenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/citizens")
//public class CitizenController {
//
//    @Autowired
//    private CitizenService citizenService;
//
//    @PostMapping("/createCitizen")
//    public Citizen createCitizen(@RequestBody CitizenRequestDTO citizen) {
//        return citizenService.createCitizen(citizen);
//    }
//
//    @GetMapping("/getCitizenById/{id}")
//    public Citizen getCitizenById(@PathVariable int id) {
//        return citizenService.getCitizenById(id);
//    }
//
//
//    @PutMapping("/update/{id}")
//    public Citizen updateCitizen(@PathVariable int id, @RequestBody CitizenRequestDTO citizen) {
//        return citizenService.updateCitizen(id, citizen);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteCitizen(@PathVariable int id) {
//        citizenService.deleteCitizen(id);
//    }
//}

package com.example.disasterrelief.controller;

import com.example.disasterrelief.dto.request.CitizenRequestDTO;
import com.example.disasterrelief.entity.Citizen;
import com.example.disasterrelief.service.CitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/createCitizen")
    public Citizen createCitizen(@Valid @RequestBody CitizenRequestDTO requestDTO) {
        return citizenService.createCitizenWithUser(requestDTO);
    }

    @GetMapping("/getCitizenById/{id}")
    public Citizen getCitizenById(@PathVariable int id) {
        return citizenService.getCitizenById(id);
    }

    @GetMapping("/getAllCitizens")
    public List<Citizen> getAllCitizens() {
        return citizenService.getAllCitizens();
    }

    @PutMapping("/update/{id}")
    public Citizen updateCitizen(@PathVariable int id, @Valid @RequestBody CitizenRequestDTO requestDTO) {
        Citizen existing = citizenService.getCitizenById(id);
        if (existing != null) {
            existing.setDob(requestDTO.getDob());
            existing.setGender(requestDTO.getGender());
            existing.setAddress(requestDTO.getAddress());
            existing.setContactInfo(requestDTO.getPhone()); // Keep synced
            return citizenService.updateCitizen(id, existing);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCitizen(@PathVariable int id) {
        citizenService.deleteCitizen(id);
    }
}