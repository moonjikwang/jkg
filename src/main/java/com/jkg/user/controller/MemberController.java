package com.jkg.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkg.user.dto.MemberRequestDto;
import com.jkg.user.dto.MemberResponseDto;
import com.jkg.user.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Member API", description = "사용자 관련 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 생성 API
     * 
     * @Method create
     * @Param MemberRequestDto
     * @Return ResponseEntity<MemberResponseDto>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "회원 생성", description = "새로운 회원을 생성합니다.")
    @PostMapping
    public ResponseEntity<MemberResponseDto> create(@Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok(memberService.create(dto));
    }

    /**
     * 부서 내 회원 목록 조회 API
     * 
     * @Method listByDepartment
     * @Return ResponseEntity<List<MemberResponseDto>>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "부서 내 회원 목록 조회", description = "부서ID로 회원 목록을 조회합니다.")
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<MemberResponseDto>> listByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(memberService.findByDepartment(departmentId));
    }
}
