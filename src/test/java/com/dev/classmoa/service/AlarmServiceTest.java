package com.dev.classmoa.service;

import java.util.List;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.Lecture.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.CreateAlarmResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AlarmServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AlarmService alarmService;

    @Autowired
    AlarmRepository alarmRepository;

    @Test
    @DisplayName("알람을 신청한다.")
    @Rollback(value = false)
    void createAlarm() {
        // given
        String lectureId = "artstudyjwLee508";
        Member member = memberRepository.findById(1L).get();

        // when
        CreateAlarmResponse response = alarmService.createAlarm(lectureId, member);
        Long savedAlarmId = response.getAlarmId();

        // then
        assertThat(alarmRepository.findById(2L).get().getMember())
                .isEqualTo(member);
    }

    @Test
    @DisplayName("알람 내역을 조회한다")
    void getAlarmList() {
        // given
        Long memberId = 1L;

        // when
        List<FindAlarmLecturesResponse> alarmList = alarmService.getAlarmListByMember(memberId);

        // then
        assertThat(alarmList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("알람을 취소한다")
    void cancelAlarm() {
        // given
        Long alarmId = 2L;
        Member member = memberRepository.findById(1L).get();

        // when
        alarmService.cancelAlarm(alarmId, member);

        // then
        assertThat(alarmRepository.findById(alarmId).isPresent())
                .isFalse();

    }
}