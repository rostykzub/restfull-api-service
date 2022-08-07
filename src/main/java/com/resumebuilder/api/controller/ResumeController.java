package com.resumebuilder.api.controller;

import com.resumebuilder.api.entities.ResumeEntity;
import com.resumebuilder.api.exception.ResumeNameNullOrEmptyException;
import com.resumebuilder.api.exception.ResumeNotFoundException;
import com.resumebuilder.api.exception.UserNotFoundException;
import com.resumebuilder.api.helpers.GetCurrentUserIdService;
import com.resumebuilder.api.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
public class ResumeController {

    @Autowired
    ResumeService resumeService;
    @Autowired
    GetCurrentUserIdService getCurrentUserIdService;

    @PostMapping("/{userId}/resumes")
    @PreAuthorize("@getCurrentUserIdService.hasId(#userId)")
    public ResponseEntity saveResume(@RequestBody ResumeEntity resume, @PathVariable long userId){
        try{
            return ResponseEntity.ok(resumeService.createResume(resume,userId));
        }
        catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ResumeNameNullOrEmptyException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e);
        }
    }

    @PutMapping("/{userId}/resumes/{resumeId}")
    @PreAuthorize("@getCurrentUserIdService.hasId(#userId)")
    public ResponseEntity updateResume(@PathVariable long userId, @PathVariable long resumeId, @RequestBody ResumeEntity resume){
        try{
            return ResponseEntity.ok(resumeService.updateResume(resume,resumeId));
        }
        catch (ResumeNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e);
        }
    }

    @DeleteMapping("/{userId}/resumes/{resumeId}")
    @PreAuthorize("@getCurrentUserIdService.hasId(#userId)")
    public ResponseEntity deleteResume(@PathVariable long userId, @PathVariable long resumeId){
        try{
            return ResponseEntity.ok(resumeService.deleteResume(resumeId));
        }
        catch (ResumeNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Server side error occurred: "+e);
        }
    }
}