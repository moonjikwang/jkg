package com.jkg.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jkg.user.domain.Department;
import com.jkg.user.domain.Organization;
import com.jkg.user.dto.DepartmentRequestDto;
import com.jkg.user.dto.DepartmentResponseDto;
import com.jkg.user.repository.DepartmentRepository;
import com.jkg.user.repository.OrganizationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public DepartmentResponseDto create(DepartmentRequestDto dto) {
        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));

        Department department = new Department(dto.getName(), org);
        departmentRepository.save(department);

        return new DepartmentResponseDto(
                department.getId(),
                department.getName(),
                org.getId()
        );
    }

    public List<DepartmentResponseDto> findByOrganization(Long organizationId) {
        List<Department> list = departmentRepository.findByOrganizationId(organizationId);
        return list.stream()
                .map(d -> new DepartmentResponseDto(d.getId(), d.getName(), d.getOrganization().getId()))
                .collect(Collectors.toList());
    }
}
