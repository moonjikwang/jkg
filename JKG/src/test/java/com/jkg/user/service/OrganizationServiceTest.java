package com.jkg.user.service;

import com.jkg.user.domain.Organization;
import com.jkg.user.dto.OrganizationRequestDto;
import com.jkg.user.dto.OrganizationResponseDto;
import com.jkg.user.repository.OrganizationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    @DisplayName("조직 생성 및 조회 테스트")
    void createAndFindOrganization() {
        // given
        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setName("개발팀");

        // when
        OrganizationResponseDto response = organizationService.create(dto);

        // then
        Organization found = organizationRepository.findById(response.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("개발팀");
    }
}
