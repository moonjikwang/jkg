package com.jkg.user.service;

import com.jkg.user.domain.Department;
import com.jkg.user.domain.Organization;
import com.jkg.user.repository.DepartmentRepository;
import com.jkg.user.repository.OrganizationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DepartmentServiceTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("부서 생성 및 조회 테스트")
    void createAndFindDepartment() {
        // given
        Organization org = new Organization("사업부");
        organizationRepository.save(org);

        // when
        Department dept = new Department("마케팅팀", org);
        departmentRepository.save(dept);

        // then
        Department found = departmentRepository.findById(dept.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("마케팅팀");
        assertThat(found.getOrganization().getName()).isEqualTo("사업부");
    }
}
