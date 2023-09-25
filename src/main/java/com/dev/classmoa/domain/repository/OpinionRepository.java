package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
}
