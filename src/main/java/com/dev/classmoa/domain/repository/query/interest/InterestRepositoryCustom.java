package com.dev.classmoa.domain.repository.query.interest;

import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepositoryCustom {
    Optional<InterestLecture> getInterestLecture(Long memberId, String lectureId);

    List<Lecture> getInterestLectures(Long memberId);
}
