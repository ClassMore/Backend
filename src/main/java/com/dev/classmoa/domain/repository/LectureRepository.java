package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Lecture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
	Optional<Lecture> findByLectureIdAndDate(String lectureId, LocalDate date);
	Page<Lecture> findAllByDate(Pageable pageable, LocalDate date);
}
