package com.jkg.user.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jkg.user.domain.enums.MemberStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = MemberStatus.ACTIVE; // 기본 상태
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Member(String username, String password, String name, Department department, MemberStatus status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.department = department;
        this.status = status;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }

    public void changeDepartment(Department department) {
        this.department = department;
    }

    /**
     * UserDetails 구현 메서드
     */

    /**
     * 사용자 권한 정보 반환
     * 기본 ROLE_USER 부여
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 계정 만료 여부
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 여부 - SUSPENDED 상태면 잠금 처리
     */
    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(MemberStatus.SUSPENDED);
    }

    /**
     * 비밀번호 만료 여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성화 여부 - ACTIVE 상태일 때만 활성화로 간주
     */
    @Override
    public boolean isEnabled() {
        return status.equals(MemberStatus.ACTIVE);
    }
}
