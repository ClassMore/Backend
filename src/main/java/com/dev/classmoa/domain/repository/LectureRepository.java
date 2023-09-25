package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
	Lecture finByLectureId(String lectureId);
}
