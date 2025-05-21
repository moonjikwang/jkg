package com.jkg.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;		// 조직 이름

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "organization")
    private List<Department> departments;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Organization(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
