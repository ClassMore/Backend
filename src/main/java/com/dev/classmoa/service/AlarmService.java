package com.dev.classmoa.service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.dto.Lecture.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.CreateAlarmResponse;
import com.dev.classmoa.dto.alarm.response.FindAlarmResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {
	private final AlarmRepository alarmRepository;
	private final LectureService lectureService;

	// 조회
	public List<FindAlarmLecturesResponse> getAlarmListByMember(Long memberId){
		List<Alarm> alarms = alarmRepository.findAlarmsByMemberId(memberId);

		return alarms.stream()
			.map(Alarm::getLecture)
			.map(FindAlarmLecturesResponse::new)
			.toList();
	}

	// 단일 조회
	public FindAlarmResponse getIsAlarmed(String lectureId, Member member){
		Boolean isAlarmed = alarmRepository.findAlarmByMemberIdAndLecture_LectureId(member.getId(), lectureId)
			.isPresent();
		return new FindAlarmResponse(isAlarmed);
	}

	// 신청
	//TODO:반환값 왜 필요해? 트랜잭션 걸고 예외 던지기 (알람 객체 생성 하지 않기)
	@Transactional
	public void createAlarm(String lectureId, Member member){
		Lecture lecture = lectureService.getLectureDetail(lectureId);
		try {
			alarmRepository.save(
					Alarm.builder()
							.member(member)
							.lecture(lecture)
							.customPrice(lecture.getSalePrice())
							.build()
			);
		} catch(Exception e) {
			e.getMessage();
			throw e;
		}
	}

	// 삭제
	public void cancelAlarm(Long alarmId, Member member) {
		//TODO: 예외 함수 커스터마이징해서 넣기 [규민]
		Alarm alarmlecture = alarmRepository.findById(alarmId)
			.orElseThrow(() -> new IllegalArgumentException("not found"));

		//TODO: methodArgumentResolver 에서 처리하는 로직에 따라 달라질 수 있다.[규민]
		//TODO: 멤버 다를때 예외처리
		try {
			if(alarmlecture.getMember().equals(member)) {
				alarmRepository.deleteById(alarmlecture.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생 내용: " + e.getMessage());
			throw e;
		}
	}
}
