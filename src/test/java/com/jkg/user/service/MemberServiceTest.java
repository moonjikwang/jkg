package com.jkg.user.service;

import com.jkg.user.domain.Department;
import com.jkg.user.domain.Member;
import com.jkg.user.domain.Organization;
import com.jkg.user.domain.enums.MemberStatus;
import com.jkg.user.repository.DepartmentRepository;
import com.jkg.user.repository.MemberRepository;
import com.jkg.user.repository.OrganizationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    @DisplayName("회원 생성 및 조회 테스트")
    void createAndFindMember() {
        // given
        Organization org = new Organization("기술본부");
        organizationRepository.save(org);

        Department dept = new Department("개발팀", org);
        departmentRepository.save(dept);

        Member member = new Member(
                "jikwang",
                "password123",
                "지광",
                dept,
                MemberStatus.ACTIVE
        );

        memberRepository.save(member);

        // when
        Member found = memberRepository.findById(member.getId()).orElseThrow();

        // then
        assertThat(found.getUsername()).isEqualTo("jikwang");
        assertThat(found.getDepartment().getName()).isEqualTo("개발팀");
        assertThat(found.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }
}
