package com.example.disasterrelief.controller;
import jakarta.validation.Valid;
import com.example.disasterrelief.dao.DistributionRepository;
import com.example.disasterrelief.dao.ReliefItemRepository;
import com.example.disasterrelief.dto.request.ReliefItemRequestDTO;
import com.example.disasterrelief.dto.response.ReliefItemResponseDTO;
import com.example.disasterrelief.service.ReliefItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/ReliefItems")
public class ReliefItemController {

    private final ReliefItemService reliefItemService;


    public ReliefItemController(ReliefItemService reliefItemService){
        this.reliefItemService = reliefItemService;
    }

    @GetMapping("/getReliefItem")
    public List<ReliefItemResponseDTO> getReliefItem(){
        return reliefItemService.getAllReliefItem();
    }

    @GetMapping("/getReliefItemById/{id}")
    public ResponseEntity<ReliefItemResponseDTO> getReliefItemById(@PathVariable Integer id) {
        ReliefItemResponseDTO response = reliefItemService.getReliefItemById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createReliefItem")
    public ResponseEntity<ReliefItemResponseDTO> createReliefItem(@RequestBody @Valid ReliefItemRequestDTO reliefItemDto){
        return ResponseEntity.ok(reliefItemService.saveReliefItem(reliefItemDto));
    }

    @PutMapping("/updateReliefItem/{id}")
    public ResponseEntity<ReliefItemResponseDTO> updateReliefItem(
            @PathVariable Integer id,
            @RequestBody ReliefItemRequestDTO reliefItemDto
    ) {
        // Force the ID from the URL into the DTO
        reliefItemDto.setItemId(id);

        ReliefItemResponseDTO response = reliefItemService.updateReliefItem(reliefItemDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteReliefItem/{id}")
    public ResponseEntity<String> deleteReliefItem(@PathVariable("id") int id) {
        try {
            reliefItemService.deleteReliefItem(id);
            return ResponseEntity.ok("Relief Item with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


//package com.example.disasterrelief.controller;
//import com.example.disasterrelief.dao.DistributionRepository;
//import com.example.disasterrelief.dto.request.ReliefItemRequestDTO;
//import com.example.disasterrelief.dto.response.ReliefItemResponseDTO;
//import com.example.disasterrelief.service.ReliefItemService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/ReliefItems")
//public class ReliefItemController {
//
//    private final ReliefItemService reliefItemService;
//
//
//    public ReliefItemController(ReliefItemService reliefItemService){
//        this.reliefItemService = reliefItemService;
//    }
//
//    @GetMapping
//    public List<ReliefItemResponseDTO> getReliefItem(){
//        return reliefItemService.getAllReliefItem();
//    }
//
//    @PostMapping
//    public ResponseEntity<ReliefItemResponseDTO> createReliefItem(@RequestBody ReliefItemRequestDTO reliefItemDto){
//        return ResponseEntity.ok(reliefItemService.saveReliefItem(reliefItemDto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ReliefItemResponseDTO> updateReliefItem(
//            @PathVariable Integer id,
//            @RequestBody ReliefItemRequestDTO reliefItemDto
//    ) {
//        // Force the ID from the URL into the DTO
//        reliefItemDto.setItemId(id);
//
//        ReliefItemResponseDTO response = reliefItemService.updateReliefItem(reliefItemDto);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") int id) {
//        try {
//            reliefItemService.deleteReliefItem(id);
//            return ResponseEntity.ok("Relief Item with ID " + id + " deleted successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}