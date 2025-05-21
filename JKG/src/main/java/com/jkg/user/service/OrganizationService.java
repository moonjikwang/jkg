package com.jkg.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.jkg.user.domain.Organization;
import com.jkg.user.dto.OrganizationRequestDto;
import com.jkg.user.dto.OrganizationResponseDto;
import com.jkg.user.repository.OrganizationRepository;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationResponseDto create(OrganizationRequestDto dto) {
    	Organization org = new Organization(dto.getName());
        organizationRepository.save(org);

        return new OrganizationResponseDto(
                org.getId(),
                org.getName(),
                org.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}