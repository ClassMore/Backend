package com.dev.classmoa.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.alarm.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.FindAlarmResponse;
import com.dev.classmoa.service.AlarmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlarmController {
	private final AlarmService alarmService;

	// 알람내역 조회
	@GetMapping("/alarm")
	public ResponseEntity<List<FindAlarmLecturesResponse>> getAlarms(Member member){
		return ResponseEntity.ok(alarmService.getAlarmListByMember(member.getId()));
	}

	// 알람내역 한개 조회
	@GetMapping("/alarm/{lecture_id}")
	public ResponseEntity<FindAlarmResponse> getAlarm(@PathVariable("lecture_id") String lectureId, Member member){
		return ResponseEntity.ok(alarmService.getIsAlarmed(lectureId, member));
	}

	// 알람신청2
	//TODO: pathvariable 로 매핑을 시켜줄려면 이름이 같거나 @PathVariable("lecture_id")라고 선언 해야 가능한 걸로 앎 [가영]
	@PostMapping("/alarm/{lecture_id}")
	public void createAlarm(@PathVariable("lecture_id") String lectureId, Member member){
		alarmService.createAlarm(lectureId, member);
	}

	// 알람신청 해제
	// TODO: api 문서에는 Pathvariable 로 받는 것 같은데 아닌가 [창준]

	@DeleteMapping("/alarm/{alarm_id}")
	public void cancelAlarm(@PathVariable("alarm_id") @Valid Long alarmId, Member member){
		alarmService.cancelAlarm(alarmId, member);
	}

}
