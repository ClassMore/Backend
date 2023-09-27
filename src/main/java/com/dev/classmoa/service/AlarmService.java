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

	// 단일 조회
	public Boolean getIsAlarmed(String lectureId, Member member){
		return alarmRepository.existsAlarmByMemberIdAndLectureId(member.getId(), lectureId);
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
	public void cancel(Long alarmId, Member member) {
		//TODO: 예외 함수 커스터마이징해서 넣기 [규민]
		Alarm alarmlecture = alarmRepository.findById(alarmId)
			.orElseThrow(() -> new IllegalArgumentException("not found"));

		//TODO: methodArgumentResolver 에서 처리하는 로직에 따라 달라질 수 있다.[규민]
		if(alarmlecture.getMember().equals(member)) {
			alarmRepository.deleteById(alarmlecture.getId());
		}
	}
}
