package com.dev.classmoa.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.LectureTag;

import com.dev.classmoa.domain.repository.query.alarm.AlarmRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {
	List<Alarm> findAlarmsByMemberIdAndLectureDateAndAndCanceledIsFalse(Long memberId, LocalDate now);
}
