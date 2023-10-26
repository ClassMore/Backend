package com.dev.classmoa.domain.repository;

import com.dev.classmoa.domain.entity.Opinion;
import com.dev.classmoa.domain.repository.query.opinion.OpinionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long>, OpinionRepositoryCustom {
    List<Opinion> findAllByLecture_LectureIdAndIsDeleted(String lectureId, Boolean isDeleted);
}
