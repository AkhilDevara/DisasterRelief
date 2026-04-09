package com.example.disasterrelief.dao;
import com.example.disasterrelief.entity.ReliefItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReliefItemRepository extends JpaRepository<ReliefItem , Integer> {

    ReliefItem findByName(String name);
}
