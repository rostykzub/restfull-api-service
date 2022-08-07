package com.resumebuilder.api.entities;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class ResumeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name,surname,position;

    @ManyToOne @JoinColumn(name = "user_id")
    private UserEntity user;



}
