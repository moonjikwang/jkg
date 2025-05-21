package com.jkg.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationResponseDto {
    private Long id;
    private String name;
    private String createdAt;

    public OrganizationResponseDto(Long id, String name, String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
}