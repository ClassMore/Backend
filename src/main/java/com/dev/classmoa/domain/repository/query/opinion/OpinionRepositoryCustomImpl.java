package com.dev.classmoa.domain.repository.query.opinion;

import com.dev.classmoa.domain.entity.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class OpinionRepositoryCustomImpl extends QuerydslRepositorySupport implements OpinionRepositoryCustom {

    public OpinionRepositoryCustomImpl() {
        super(InterestLecture.class);
    }

    @Override
    public List<Opinion> getOpinions(String lectureId) {
        QOpinion opinion = QOpinion.opinion;
        QLecture lecture = QLecture.lecture;

        BooleanBuilder lectureCondition = new BooleanBuilder();

        Lecture todayLecture = from(lecture).where(lectureCondition
                .and(lecture.lectureId.eq(lectureId))
                .and(lecture.date.eq(LocalDate.now()))).fetchFirst();

        BooleanBuilder interestCondition = new BooleanBuilder();

        return from(opinion)
                .where(interestCondition
                        .and(opinion.lecture.eq(todayLecture))
                        .and(opinion.isDeleted.eq(false)))
                .fetch();
    }

    @Override
    public Optional<Opinion> getOpinion(String lectureId, Long id) {
        QOpinion opinion = QOpinion.opinion;
        QLecture lecture = QLecture.lecture;

        BooleanBuilder lectureCondition = new BooleanBuilder();

        Lecture todayLecture = from(lecture).where(lectureCondition
                .and(lecture.lectureId.eq(lectureId))
                .and(lecture.date.eq(LocalDate.now())))
                .fetchFirst();

        BooleanBuilder opinionCondition = new BooleanBuilder();

        return Optional.of(from(opinion)
                .where(opinionCondition
                        .and(opinion.id.eq(id))
                        .and(opinion.lecture.eq(todayLecture))
                        .and(opinion.isDeleted.eq(false)))
                .fetchFirst());
    }
}
