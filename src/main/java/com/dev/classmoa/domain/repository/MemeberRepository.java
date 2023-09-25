package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeberRepository extends JpaRepository<Member, Long> {
}
