package com.dev.classmoa.domain.repository.query.comment;

import com.dev.classmoa.domain.entity.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CommentRepositoryCustomImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    public CommentRepositoryCustomImpl() {
        super(InterestLecture.class);
    }

    @Override
    public List<Comment> getComments(Opinion opinion, String lectureId) {
        QLecture lecture = QLecture.lecture;
        QComment comment = QComment.comment;

        BooleanBuilder lectureCondition = new BooleanBuilder();

        Lecture todayLecture = from(lecture).where(lectureCondition
                .and(lecture.lectureId.eq(lectureId))
                .and(lecture.date.eq(LocalDate.now()))).fetchFirst();

        BooleanBuilder interestCondition = new BooleanBuilder();
        // TODO: select * from interest where lecture_id=? and member_id=?;

        return from(comment)
                .where(interestCondition
                        .and(comment.opinion.eq(opinion))
                        .and(comment.isDeleted.eq(false)))
                .fetch();
    }
}
