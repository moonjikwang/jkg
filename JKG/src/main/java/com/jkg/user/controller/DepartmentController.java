package com.jkg.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkg.user.dto.DepartmentRequestDto;
import com.jkg.user.dto.DepartmentResponseDto;
import com.jkg.user.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Department API", description = "부서 관련 API")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 부서 생성
     * 
     * @Method create
     * @Return ResponseEntity<DepartmentResponseDto>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "부서 생성", description = "새로운 부서를 생성합니다.")
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.create(dto));
    }

    /**
     * 부서 목록 조회
     * 
     * @Method listByOrganization
     * @Return ResponseEntity<List<DepartmentResponseDto>>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "부서 목록 조회", description = "조직ID로 부서 목록을 조회합니다.")
    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<DepartmentResponseDto>> listByOrganization(@PathVariable Long orgId) {
        return ResponseEntity.ok(departmentService.findByOrganization(orgId));
    }
}