package com.example.classmoa.domain.repository;

import com.example.classmoa.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeberRepository extends JpaRepository<Member, Long> {
}
