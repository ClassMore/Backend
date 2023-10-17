package com.dev.classmoa.domain.repository;

import java.util.Optional;

import com.dev.classmoa.domain.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
	Optional<EmailVerification> findByEmail(String email);
}
