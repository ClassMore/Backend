package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.LectureTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureTagRepository extends JpaRepository<LectureTag, Long> {
    List<LectureTag> findLectureTagByTagId(Long id);
}
