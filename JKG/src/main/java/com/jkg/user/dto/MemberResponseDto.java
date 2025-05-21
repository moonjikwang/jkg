package com.jkg.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String username;
    private String name;
    private Long departmentId;
    private String status;
}