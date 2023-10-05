package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.domain.repository.LectureRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.alarm.response.FindAlarmLecturesResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AlarmServiceTest {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AlarmService alarmService;

    @Autowired
    AlarmRepository alarmRepository;

    @AfterEach
    public void init(){

    }

    @BeforeEach
    void CreateTestData() {
        Lecture lecture1 = Lecture.builder()
                .companyName("카클스")
                .imageLink("어딘가 있음")
                .lectureId("카클스1")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.ybm.com")
                .title("오사카 여행 가이드")
                .build();

        Lecture lecture2 = Lecture.builder()
                .lectureId("카클스2")
                .companyName("카클스")
                .imageLink("어딘가 있음")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.ybm.com")
                .title("오사카 여행 가이드")
                .build();

        Lecture lecture3 = Lecture.builder()
                .lectureId("카클스3")
                .companyName("카클스")
                .imageLink("임창준 아이폰")
                .instructor("임창준")
                .ordinaryPrice(100000)
                .salePercent("30%")
                .salePrice(70000)
                .siteLink("www.창준교토.gg")
                .title("교토 여행 가이드")
                .build();

        lectureRepository.save(lecture1);
        lectureRepository.save(lecture2);
        lectureRepository.save(lecture3);


        Member member = Member.signup()
                .email("123@gmail.com")
                .password("123")
                .birthDate(LocalDate.now())
                .nickname("동그리")
                .signupbuild();
        Member savedMember = memberRepository.save(member);
        System.out.println("savedMember = " + savedMember.getId());

        Alarm alarm1 = Alarm.builder()
                .member(savedMember)
                .lecture(lecture1)
                .customPrice(100000)
                .build();

        Alarm alarm2 = Alarm.builder()
                .member(savedMember)
                .lecture(lecture2)
                .customPrice(90000)
                .build();

        alarmRepository.save(alarm1);
        Alarm alarm = alarmRepository.save(alarm2);
        System.out.println("alarm.getCustomPrice() = " + alarm.getCustomPrice());
    }

    @Test
    @DisplayName("lectureId와 회원정보를 받아서 해당 강의에 대한 알람을 신청한다.")
    void createAlarm() {
        // given
        String lectureId = "카클스3";
        Member member = memberRepository.findById(1L).get();

        // when
        alarmService.createAlarm(lectureId, member);

        // then
        assertThat(alarmRepository.findById(3L).get().getMember())
                .isEqualTo(member);
    }

    @Test
    @DisplayName("회원정보를 받아서 알람 내역을 조회한다")
    void getAlarmList() {
        // given
        Long memberId = 1L;

        // when
        List<FindAlarmLecturesResponse> alarmList = alarmService.getAlarmListByMember(memberId);

        // then
        assertThat(alarmList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("lectureId와 회원정보를 받아서 알람을 취소한다")
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