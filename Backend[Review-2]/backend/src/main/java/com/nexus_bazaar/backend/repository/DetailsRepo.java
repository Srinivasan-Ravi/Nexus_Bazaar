package com.nexus_bazaar.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus_bazaar.backend.model.Details;

public interface DetailsRepo extends JpaRepository<Details, Long> {
}
