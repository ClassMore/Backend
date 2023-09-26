package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.InterestLecture;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Lecture.response.FindAlarmLectures;
import com.dev.classmoa.service.AlarmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlarmController {
	private final AlarmService alarmService;

	// 알람내역 조회
	@GetMapping("/alarm")
	public ResponseEntity<List<FindAlarmLectures>> getAlarms(Member member){
		return ResponseEntity.ok(alarmService.getLectureListByMember(member.getId()));
	}

	// 알람신청
	@PostMapping("/alarm/{lecture_id}")
	public Long createAlarm(@PathVariable String lectureId, Member member){
		return alarmService.create(lectureId, member);
	}

	// 알람신청 해제
	@DeleteMapping("/alarm")
	public void deleteAlarm(Alarm alarm, Member member){
		alarmService.delete(alarm, member);
	}

}
