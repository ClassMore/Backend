package com.dev.classmoa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.dto.Lecture.response.FindAlarmLectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {
	private final AlarmRepository alarmRepository;
	private final LectureService lectureService;

	// 조회
	public List<FindAlarmLectures> getLectureListByMember(Long memberId){
		List<Alarm> alarms = alarmRepository.findAlarmsByMemberId(memberId);

		return alarms.stream()
			.map(Alarm::getLecture)
			.map(FindAlarmLectures::new)
			.toList();
	}

	// 신청
	public Long create(String lectureId, Member member){
		Lecture lecture = lectureService.getLectureDetail(lectureId);
		return alarmRepository.save(
			Alarm.builder()
				.member(member)
				.lecture(lecture)
				.customPrice(lecture.getSalePrice())
				.build()
		).getId();
	}

	// 삭제
	public void delete(Alarm alarm, Member member) {
		Alarm alarmlecture = alarmRepository.findById(alarm.getId())
			.orElseThrow(() -> new IllegalArgumentException("not found"));
		if(alarmlecture.getMember().equals(member)) {
			alarmRepository.deleteById(alarmlecture.getId());
		}
	}
}
