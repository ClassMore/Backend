package com.dev.classmoa.domain.repository.query.alarm;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Lecture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepositoryCustom {
    Optional<Alarm> getAlarm(Long memberId, String lectureId);

    List<Lecture> getAlarms(Long memberId);
}
