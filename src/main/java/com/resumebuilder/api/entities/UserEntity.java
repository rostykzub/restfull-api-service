package com.resumebuilder.api.entities;

import com.resumebuilder.api.enums.Roles;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String username;

    @Column
    @Size(min = 1, message = "Password must be at least 1 character long")
    private String password;

    @Column
    private Boolean isActive;

    @Column
    //@Email(message = "Invalid email format")
    private String email;

    @Column
    //@Email(message = "Invalid email format")
    private String phone;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @Column
    @OneToMany(fetch=FetchType.EAGER, cascade = ALL, mappedBy = "user")
    private List<ResumeEntity> resumes;
}
