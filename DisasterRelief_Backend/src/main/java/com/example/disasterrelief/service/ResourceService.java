//package org.cognizant.disastermanagement.service;
//
//import org.cognizant.disastermanagement.entity.RecoveryProgram;
//import org.cognizant.disastermanagement.entity.Resource;
//import org.cognizant.disastermanagement.dao.RecoveryProgramRepository;
//import org.cognizant.disastermanagement.dao.ResourceRepository;
//import org.cognizant.disastermanagement.Enum.ResourceStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Service
//public class ResourceService {
//
//    @Autowired
//    private ResourceRepository resourceRepo;
//
//    @Autowired
//    private RecoveryProgramRepository programRepo;
//
//    @Transactional
//    public void addResource(int programId, Resource resource) {
//        RecoveryProgram program = programRepo.findById(programId)
//                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + programId));
//        resource.setRecoveryProgram(program);
//        resourceRepo.save(resource);
//    }
//
//    @Transactional
//    public Resource consumeResource(int resourceId, double amount) {
//        Resource res = resourceRepo.findById(resourceId)
//                .orElseThrow(() -> new RuntimeException("Resource not found"));
//
//        if (res.getQuantity() < amount) {
//            throw new RuntimeException("Insufficient quantity available!");
//        }
//
//        res.setQuantity(res.getQuantity() - amount);
//
//        // Monitoring Logic: Auto-update status based on usage
//        if (res.getQuantity() == 0) {
//            res.setStatus(ResourceStatus.Consumed);
//        } else {
//            res.setStatus(ResourceStatus.InUse);
//        }
//
//        return resourceRepo.save(res);
//    }
//
//    public List<Resource> getAllResources() {
//        return resourceRepo.findAll();
//    }
//}
package com.example.disasterrelief.service;

import com.example.disasterrelief.entity.RecoveryProgram;
import com.example.disasterrelief.entity.Resource;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.entity.AuditLog;
import com.example.disasterrelief.dao.RecoveryProgramRepository;
import com.example.disasterrelief.dao.ResourceRepository;
import com.example.disasterrelief.dao.UserRepository;
import com.example.disasterrelief.dao.AuditLogRepository;
import com.example.disasterrelief.Enum.ResourceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private RecoveryProgramRepository programRepo;

    @Autowired
    private UserRepository userRepo; // Needed to find the Manager

    @Autowired
    private AuditLogRepository auditLogRepo; // Needed to save the security log

    @Transactional
    public void addResource(int programId, Resource resource) {
        RecoveryProgram program = programRepo.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + programId));
        resource.setRecoveryProgram(program);
        resourceRepo.save(resource);
    }

    /**
     * Updated consumeResource to handle receiver history and audit logging.
     */
    @Transactional
    public Resource consumeResource(int resourceId, double amount, String receiverName, int managerId) {
        // 1. Update Resource Inventory
        Resource res = resourceRepo.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        if (res.getQuantity() < amount) {
            throw new RuntimeException("Insufficient quantity available! Current: " + res.getQuantity());
        }

        res.setQuantity(res.getQuantity() - amount);

        // Append to the ReceivedBy history string
        String currentHistory = (res.getReceivedBy() == null) ? "" : res.getReceivedBy();
        String newEntry = receiverName + " (" + amount + " " + res.getUnit() + ")";
        res.setReceivedBy(currentHistory.isEmpty() ? newEntry : currentHistory + " | " + newEntry);

        // Monitoring Logic: Auto-update status
        if (res.getQuantity() == 0) {
            res.setStatus(ResourceStatus.CONSUMED);
        } else {
            res.setStatus(ResourceStatus.INUSE);
        }

        Resource updatedResource = resourceRepo.save(res);

        // 2. Create Audit Log Entry for Security
        User manager = userRepo.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager/User not found with ID: " + managerId));

        AuditLog log = new AuditLog();
        log.setUser(manager);
        log.setAction("CONSUME");
        log.setResource("RESOURCE_TABLE"); // Labeling the module changed
        log.setTimestamp(LocalDateTime.now());
        log.setDetails("Allocated " + amount + " " + res.getUnit() + " of " + res.getName() + " to " + receiverName);

        auditLogRepo.save(log);

        return updatedResource;
    }

    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }
}