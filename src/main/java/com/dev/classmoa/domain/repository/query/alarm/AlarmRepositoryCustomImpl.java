package com.dev.classmoa.domain.repository.query.alarm;

import com.dev.classmoa.domain.entity.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AlarmRepositoryCustomImpl extends QuerydslRepositorySupport implements AlarmRepositoryCustom {

    public AlarmRepositoryCustomImpl() {
        super(Alarm.class);
    }

    @Override
    public Optional<Alarm> getAlarm(Long memberId, String lectureId) {
        QAlarm alarm = QAlarm.alarm;
        QLecture lecture = QLecture.lecture;

        BooleanBuilder lectureCondition = new BooleanBuilder();


        Lecture todayLecture = from(lecture).where(lectureCondition.and(lecture.lectureId.eq(lectureId))
                .and(lecture.date.eq(LocalDate.now()))).fetchFirst();
        BooleanBuilder alarmCondition = new BooleanBuilder();
        // TODO: select * from interest where lecture_id=? and member_id=?;


        Alarm query = from(alarm)
                .where(alarmCondition.and(alarm.member.id.eq(memberId))
                        .and(alarm.lecture.eq(todayLecture))).fetchFirst();
        return Optional.ofNullable(query);
    }

    @Override
    public List<Lecture> getAlarms(Long memberId) {
        QAlarm alarm = QAlarm.alarm;
        QLecture lecture = QLecture.lecture;
        QMember member = QMember.member;

        /*
         TODO: select * from alarm a join lecture l on l.lectureId = a.lectureId where
          l.date=now and l.memberId = memberId
         */
        return from(lecture).join(alarm)
                .on(alarm.member.id.eq(memberId)
                        .and(alarm.lecture.lectureId.eq(lecture.lectureId))
                        .and(alarm.canceled.isFalse()))
                .where(lecture.date.eq(LocalDate.now()))
                .fetchJoin().stream().toList();
    }
}
