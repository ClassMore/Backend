package com.example.classmoa.domain.repository;

import com.example.classmoa.domain.entity.SocialLoginPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginPlatformRepository extends JpaRepository<SocialLoginPlatform, Long> {
}
