package com.dev.classmoa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.dto.alarm.response.CreateAlarmResponse;

class AlarmServiceTest {

	@Test
	@DisplayName("알람 신청한 강의 목록을 보여준다.")
	void createAlarm() {
		// given
		// String lectureId = "artstudyjwLee508";
		// Member member =
		// // when
		// CreateAlarmResponse response = AlarmService.create(, member);
		// Long savedAlarmId = response.getAlarmId();
		//
		// // then
		// Password savedAlarm = AlarmRepository.findById(savedAlarmId);
		// assertThat(savedAlarm.getAlarmId()).isEqualTo(savedAlarmId);
	}

	@Test
	void getLectureListByMember() {
	}

	@Test
	void getIsAlarmed() {
	}

	@Test
	void create() {
	}

	@Test
	void cancel() {
	}
}