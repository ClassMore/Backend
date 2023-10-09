package com.dev.classmoa.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dev.classmoa.domain.entity.InterestLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestLectureRepository extends JpaRepository<InterestLecture, Long> {
	List<InterestLecture> findInterestLecturesByMemberMemberName(String memberName);
	Optional<InterestLecture> findInterestLectureByMemberIdAndLecture_LectureId(Long memberId, String lectureId);
}
