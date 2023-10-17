package com.dev.classmoa.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.classmoa.domain.entity.ViewCount;

public interface ViewCountRepository extends JpaRepository<ViewCount, Long> {

	Optional<ViewCount> findByLectureLectureIdAndLectureDate(String lectureId, LocalDate now);
}
