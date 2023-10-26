package com.dev.classmoa.domain.repository.query.opinion;

import com.dev.classmoa.domain.entity.Opinion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionRepositoryCustom {
    List<Opinion> getOpinions(String lectureId);

    Optional<Opinion> getOpinion(String lectureId, Long opnionId);
}
