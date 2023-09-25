package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
}
