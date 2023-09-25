package com.example.classmoa.domain.repository;

import com.example.classmoa.domain.entity.Lecture;
import com.example.classmoa.domain.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
	Lecture finByLectureId(String lectureId);
}