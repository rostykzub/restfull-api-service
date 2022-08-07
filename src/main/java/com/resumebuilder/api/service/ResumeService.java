package com.resumebuilder.api.service;

import com.resumebuilder.api.entities.ResumeEntity;
import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.exception.ResumeNameNullOrEmptyException;
import com.resumebuilder.api.exception.ResumeNotFoundException;
import com.resumebuilder.api.exception.UserNotFoundException;
import com.resumebuilder.api.helpers.Checks;
import com.resumebuilder.api.model.Resume;
import com.resumebuilder.api.repo.ResumeRepo;
import com.resumebuilder.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    Checks checks = new Checks();

    @Autowired
    ResumeRepo resumeRepo;
    @Autowired
    UserRepo userRepo;

    public Resume createResume(ResumeEntity resume, Long userId) throws UserNotFoundException, ResumeNameNullOrEmptyException {
        if (userRepo.findById(userId).isPresent() == false){
            throw new UserNotFoundException("Error: User with id "+userId+" not found");
        }
        if (checks.isEmptyOrNull(resume.getName())){
            throw new ResumeNameNullOrEmptyException("Error: Resume's 'name' field is is not defined or empty");
        }
        UserEntity user = userRepo.findById(userId).get();
        resume.setUser(user);
        return Resume.toModel(resumeRepo.save(resume));
    }

    public Resume updateResume(ResumeEntity resume,Long resumeId) throws ResumeNotFoundException {
        if (resumeRepo.findById(resumeId).isPresent() == false){
            throw new ResumeNotFoundException("Error: Resume with id "+resumeId+" not found");
        }
        ResumeEntity updatedResumeEntity = resumeRepo.findById(resumeId).get();
        if (!checks.isEmptyOrNull(resume.getName())) {
            updatedResumeEntity.setName(resume.getName());
        };
        if (!checks.isEmptyOrNull(resume.getSurname())) {
            updatedResumeEntity.setSurname(resume.getSurname());
        }
        if (resume.getPosition() != null){
            updatedResumeEntity.setPosition(resume.getPosition());
        }
        return Resume.toModel(resumeRepo.save(updatedResumeEntity));
    }

    public String deleteResume(Long resumeId) throws ResumeNotFoundException {
        if (resumeRepo.findById(resumeId).isPresent() == false){
            throw new ResumeNotFoundException("Error: Resume with id "+resumeId+" not found");
        }
        resumeRepo.deleteById(resumeId);
        return "Resume with id "+resumeId+" is successfully deleted";
    }

}
