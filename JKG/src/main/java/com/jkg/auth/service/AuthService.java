package com.jkg.auth.service;

import com.jkg.auth.dto.LoginRequestDto;
import com.jkg.auth.dto.LoginResponseDto;
import com.jkg.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인 처리 및 JWT 토큰 발급
     * 
     * @Method login
     * @Return LoginResponseDto
     * @Author jikwa
     * @Date 2025. 5. 21.
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
            )
        );

        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponseDto(token);
    }
}
