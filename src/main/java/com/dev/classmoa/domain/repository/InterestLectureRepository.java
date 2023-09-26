package com.dev.classmoa.domain.repository;

import java.util.List;

import com.dev.classmoa.domain.entity.InterestLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestLectureRepository extends JpaRepository<InterestLecture, Long> {
	List<InterestLecture> findInterestLecturesByMemberId(Long memberId);
}
