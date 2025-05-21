package com.jkg.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;		// 직급 이름
    private Integer level;		// 직급 레벨 (낮을수록 상위 직급)

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
