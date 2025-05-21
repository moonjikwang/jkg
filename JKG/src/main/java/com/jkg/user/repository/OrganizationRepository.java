package com.jkg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jkg.user.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}