package com.resumebuilder.api.model;

import com.resumebuilder.api.entities.ResumeEntity;

public class Resume {
    private Long id;
    private String name;
    private String surname;
    private String position;

    public static Resume toModel(ResumeEntity entity){
        Resume model = new Resume();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPosition(entity.getPosition());
        model.setSurname(entity.getSurname());
        return model;
    }

    public Resume() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
