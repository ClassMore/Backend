package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.alarm.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.FindAlarmResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final LectureService lectureService;
    private final MemberService memberService;

    // 조회
    public List<FindAlarmLecturesResponse> getAlarmListByMember(Long memberId) {
        List<Alarm> alarms = alarmRepository.findAlarmsByMemberIdAndLectureDateAndAndCanceledIsFalse(memberId, LocalDate.now());

        return alarms.stream()
                .map(Alarm::getLecture)
                .map(FindAlarmLecturesResponse::new)
                .toList();
    }

    // 단일 조회
    public FindAlarmResponse getIsAlarmed(String lectureId, LoggedInMember loggedInMember) {
        Alarm alarm = alarmRepository
                .findAlarmByMemberIdAndLecture_LectureIdAndCanceledIsFalse(loggedInMember.getMemberId(), lectureId)
                .orElseGet(Alarm::new);

        boolean isAlarmed = !alarm.isCanceled() && alarm.getId() != null;

        return new FindAlarmResponse(isAlarmed);
    }

    // 신청
    //TODO:반환값 왜 필요해? 트랜잭션 걸고 예외 던지기 (알람 객체 생성 하지 않기)
    @Transactional
    public Void createAlarm(String lectureId, LoggedInMember loggedInMember) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        Member member = memberService.findMemberById(loggedInMember.getMemberId());

        Alarm alarm = alarmRepository.findAlarmByMemberIdAndLecture_LectureIdAndCanceledIsFalse(member.getId(), lectureId)
                .orElseGet(() -> alarmRepository.save(Alarm.builder()
                        .member(member)
                        .lecture(lecture)
                        .customPrice(lecture.getSalePrice())
                        .build()));

        if (alarm.isCanceled()) {
            alarm.updateIsCanceled(false);
            return null;
        }
        alarm.updateIsCanceled(true);
        return null;
    }
}
