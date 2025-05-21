package com.jkg.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponseDto {
    private Long id;
    private String name;
    private Long organizationId;
}