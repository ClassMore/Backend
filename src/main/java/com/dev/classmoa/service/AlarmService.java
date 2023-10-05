package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Alarm;
import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.AlarmRepository;
import com.dev.classmoa.dto.alarm.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.FindAlarmResponse;
import com.dev.classmoa.exception.ClassmoaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final LectureService lectureService;

    // 조회
    public List<FindAlarmLecturesResponse> getAlarmListByMember(Long memberId) {
        List<Alarm> alarms = alarmRepository.findAlarmsByMemberId(memberId);

        return alarms.stream()
                .map(Alarm::getLecture)
                .map(FindAlarmLecturesResponse::new)
                .toList();
    }

    // 단일 조회
    public FindAlarmResponse getIsAlarmed(String lectureId, Member member) {
        Boolean isAlarmed = alarmRepository.findAlarmByMemberIdAndLecture_LectureId(member.getId(), lectureId)
                .isPresent();
        return new FindAlarmResponse(isAlarmed);
    }

    // 신청
    //TODO:반환값 왜 필요해? 트랜잭션 걸고 예외 던지기 (알람 객체 생성 하지 않기)
    @Transactional
    public void createAlarm(String lectureId, Member member) {
        Lecture lecture = lectureService.getLectureDetail(lectureId);
        if (alarmRepository.findAlarmByMemberIdAndLecture_LectureId(member.getId(), lectureId).isPresent())
            throw new ClassmoaException("알람 신청이 완료된 강의입니다.", HttpStatus.BAD_REQUEST);
        try {
            alarmRepository.save(
                    Alarm.builder()
                            .member(member)
                            .lecture(lecture)
                            .customPrice(lecture.getSalePrice())
                            .build());
        } catch (ClassmoaException ex) {
            throw new ClassmoaException("알람 신청 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제
    public void cancelAlarm(Long alarmId, Member member) {
        //TODO: 예외 함수 커스터마이징해서 넣기 [규민]
        Alarm alarmLecture = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new ClassmoaException("알림이 취소된 강의입니다.", HttpStatus.BAD_REQUEST));

        //TODO: methodArgumentResolver 에서 처리하는 로직에 따라 달라질 수 있다.[규민]
        if (alarmLecture.getMember().equals(member)) {
            alarmRepository.deleteById(alarmLecture.getId());
        }
    }
}
