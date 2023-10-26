package com.dev.classmoa.domain.repository.query.interest;

import com.dev.classmoa.domain.entity.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InterestRepositoryCustomImpl extends QuerydslRepositorySupport implements InterestRepositoryCustom {

    public InterestRepositoryCustomImpl() {
        super(InterestLecture.class);
    }

    @Override
    public Optional<InterestLecture> getInterestLecture(Long memberId, String lectureId) {
        QInterestLecture interestLecture = QInterestLecture.interestLecture;

        QLecture lecture = QLecture.lecture;

        BooleanBuilder lectureCondition = new BooleanBuilder();

        Lecture todayLecture = from(lecture).where(lectureCondition.and(lecture.lectureId.eq(lectureId))
                .and(lecture.date.eq(LocalDate.now()))).fetchFirst();

        BooleanBuilder interestCondition = new BooleanBuilder();
        // TODO: select * from interest where lecture_id=? and member_id=?;

        InterestLecture query = from(interestLecture)
                .where(interestCondition.and(interestLecture.member.id.eq(memberId))
                        .and(interestLecture.lecture.eq(todayLecture)))
                .fetchFirst();
        return Optional.ofNullable(query);
    }

    @Override
    public List<Lecture> getInterestLectures(Long memberId) {
        QInterestLecture interestLecture = QInterestLecture.interestLecture;
        QLecture lecture = QLecture.lecture;

        /*
        TODO: select * from lecture l join interest i on l.lectureId = i.lectureId where l.date=now and i.delete = false;
         */

        return from(lecture).join(interestLecture)
                .on(interestLecture.member.id.eq(memberId)
                        .and(interestLecture.lecture.lectureId.eq(lecture.lectureId))
                        .and(interestLecture.canceled.isFalse()))
                .where(lecture.date.eq(LocalDate.now()))
                .fetchJoin().stream().toList();
    }
}
