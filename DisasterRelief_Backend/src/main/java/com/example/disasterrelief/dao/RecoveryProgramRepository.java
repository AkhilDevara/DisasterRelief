package com.example.disasterrelief.dao;
import com.example.disasterrelief.entity.RecoveryProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecoveryProgramRepository extends JpaRepository<RecoveryProgram, Integer> {

}