package com.fitness.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")  // name of user table in db
@Data
public class  User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.UUID) // primary key will be auto generated
    private String id;
    @Column(unique = true, nullable = false)    // email should be unique and not null
    private String email;

    private String keycloakId;

    @Column(nullable = false)   // password should not be null
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)    // we have to use @Enumerated to tell the type of enum value passed
    private UserRole role = UserRole.USER;

    @CreationTimestamp  // this annotation will automatically assign the created time in the createdAt field
    private LocalDateTime createdAt;

    @UpdateTimestamp    // this annotation will update the updatedAt field automatically whenever any update is made in the user entity
    private LocalDateTime updatedAt;

}
