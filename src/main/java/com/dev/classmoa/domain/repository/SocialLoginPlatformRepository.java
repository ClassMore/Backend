package com.dev.classmoa.domain.repository;

import java.util.Optional;

import com.dev.classmoa.domain.entity.SocialLoginPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginPlatformRepository extends JpaRepository<SocialLoginPlatform, Long> {
	Optional<SocialLoginPlatform> findByPlatformName(String platformName);
}
