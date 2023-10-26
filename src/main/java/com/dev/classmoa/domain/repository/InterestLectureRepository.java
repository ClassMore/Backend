package com.dev.classmoa.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.repository.query.interest.InterestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestLectureRepository extends JpaRepository<InterestLecture, Long>, InterestRepositoryCustom {
	List<InterestLecture> findInterestLecturesByMemberMemberNameAndLectureDateAndCanceledIsFalse(String memberName, LocalDate now);
}
