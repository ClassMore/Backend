package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.SocialLoginPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginPlatformRepository extends JpaRepository<SocialLoginPlatform, Long> {
}
