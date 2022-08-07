package com.resumebuilder.api.model;

import com.resumebuilder.api.entities.UserEntity;
import com.resumebuilder.api.enums.Roles;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private Boolean isActive;
    private Roles role;
    private String username;
    private String email;
    private String phone;
    private List resumes;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setRole(entity.getRole());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        model.setActive(entity.getIsActive());
        if (entity.getResumes() != null){
            model.setResumes(entity.getResumes().stream().map(Resume::toModel).collect(Collectors.toList()));
        }
        return model;
    }

    public User() {
    }

    public List getResumes() {
        return resumes;
    }

    public void setResumes(List resumes) {
        this.resumes = resumes;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
