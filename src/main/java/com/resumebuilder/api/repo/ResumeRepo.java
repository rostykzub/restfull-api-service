package com.resumebuilder.api.repo;

import com.resumebuilder.api.entities.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<ResumeEntity, Long> {
    
}