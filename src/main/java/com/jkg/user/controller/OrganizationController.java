package com.jkg.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jkg.user.dto.OrganizationRequestDto;
import com.jkg.user.dto.OrganizationResponseDto;
import com.jkg.user.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Organization API", description = "조직 관련 API")
@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * 조직 생성
     * 
     * @Method create
     * @Return ResponseEntity<OrganizationResponseDto>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "조직 생성", description = "새로운 조직을 생성합니다.")
    @PostMapping
    public ResponseEntity<OrganizationResponseDto> create(@RequestBody OrganizationRequestDto dto) {
        return ResponseEntity.ok(organizationService.create(dto));
    }
}
