package com.jkg.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkg.user.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	/**
	 * 사용자 이름으로 회원정보 조회
	 * @Method findByUsername
	 * @Return Optional<Member>
	 * @Author jikwang
	 * @Date 2025. 5. 21.
	 */
	Optional<Member> findByUsername(String username);
	
	/**
	 * 부서ID로 유저 목록 조회
	 * 
	 * @Method findByDepartmentId
	 * @Return List<Member>
	 * @Author jikwang
	 * @Date 2025. 5. 21.
	 */
    List<Member> findByDepartmentId(Long departmentId);
    
}