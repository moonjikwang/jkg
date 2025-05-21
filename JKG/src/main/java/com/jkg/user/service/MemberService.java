package com.jkg.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jkg.user.domain.Department;
import com.jkg.user.domain.Member;
import com.jkg.user.domain.enums.MemberStatus;
import com.jkg.user.dto.MemberRequestDto;
import com.jkg.user.dto.MemberResponseDto;
import com.jkg.user.repository.DepartmentRepository;
import com.jkg.user.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 회원 생성
     * 
     * @Method create
     * @Param MemberRequestDto dto - 회원 생성 요청 DTO
     * @Return MemberResponseDto - 생성된 회원 정보
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    @Transactional
    public MemberResponseDto create(MemberRequestDto dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        // 비밀번호는 실제 프로젝트에서 반드시 암호화 필요 (예: BCrypt)
        Member member = new Member(dto.getUsername(), dto.getPassword(), dto.getName(), department, MemberStatus.ACTIVE);
        memberRepository.save(member);

        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getName(),
                department.getId(),
                member.getStatus().name()
        );
    }

    /**
     * 부서 내 회원 목록 조회
     * 
     * @Method findByDepartment
     * @Param Long departmentId - 부서 ID
     * @Return List<MemberResponseDto> - 회원 목록
     * @Author jikwang
     * @Date 2025. 5. 21.
     */
    public List<MemberResponseDto> findByDepartment(Long departmentId) {
        List<Member> list = memberRepository.findByDepartmentId(departmentId);
        return list.stream()
                .map(m -> new MemberResponseDto(m.getId(), m.getUsername(), m.getName(), m.getDepartment().getId(), m.getStatus().name()))
                .collect(Collectors.toList());
    }
}
