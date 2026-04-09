package com.example.disasterrelief.service;

import com.example.disasterrelief.dao.ResourceRepository;
import com.example.disasterrelief.entity.RecoveryProgram;
import com.example.disasterrelief.dao.RecoveryProgramRepository;
import com.example.disasterrelief.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecoveryService {

    @Autowired
    private RecoveryProgramRepository programRepository;

    public void createProgram(RecoveryProgram program) {
        programRepository.save(program);
    }

    public List<RecoveryProgram> getAllPrograms() {
        return programRepository.findAll();
    }

    public RecoveryProgram getProgramById(int id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + id));
    }


}
