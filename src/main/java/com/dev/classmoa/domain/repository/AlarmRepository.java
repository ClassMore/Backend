package com.dev.classmoa.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.LectureTag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
	List<Alarm> findAlarmsByMemberId(Long memberId);
	Boolean existsAlarmByMemberIdAndLectureId(Long memberId, String lectureId);
}
