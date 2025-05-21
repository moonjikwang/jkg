package com.jkg.auth.controller;

import com.jkg.auth.dto.LoginRequestDto;
import com.jkg.auth.dto.LoginResponseDto;
import com.jkg.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth API", description = "인증 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 API
     * 
     * @Method login
     * @Return ResponseEntity<LoginResponseDto>
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Operation(summary = "로그인", description = "아이디/패스워드를 통해 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
