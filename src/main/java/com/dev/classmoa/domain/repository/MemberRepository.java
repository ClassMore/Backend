package com.dev.classmoa.domain.repository;

import java.util.Optional;

import com.dev.classmoa.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByMemberName(String memberName);

}
