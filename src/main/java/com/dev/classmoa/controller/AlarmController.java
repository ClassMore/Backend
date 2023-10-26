package com.dev.classmoa.controller;

import com.dev.classmoa.auth.annotation.LoginMember;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.alarm.response.FindAlarmLecturesResponse;
import com.dev.classmoa.dto.alarm.response.FindAlarmResponse;
import com.dev.classmoa.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    // 알람내역 조회
    @GetMapping("/user/alarm")
    public ResponseEntity<List<FindAlarmLecturesResponse>> getAlarms(@LoginMember LoggedInMember loggedInMember) {
        return ResponseEntity.ok(alarmService.getAlarmListByMember(loggedInMember.getMemberId()));
    }

    // 알람내역 한개 조회
    @GetMapping("/alarm/{lecture_id}")
    public ResponseEntity<FindAlarmResponse> getAlarm(@PathVariable("lecture_id") String lectureId,
                                                      @LoginMember LoggedInMember loggedInMember) {
        return ResponseEntity.ok(alarmService.getIsAlarmed(lectureId, loggedInMember));
    }

    // 알람신청
    //TODO: pathvariable 로 매핑을 시켜줄려면 이름이 같거나 @PathVariable("lecture_id")라고 선언 해야 가능한 걸로 앎 [가영]
    @PostMapping("/user/alarm/{lecture_id}")
    public ResponseEntity<FindAlarmResponse> createAlarm(@PathVariable("lecture_id") String lectureId, @LoginMember LoggedInMember loggedInMember) {
        return ResponseEntity.ok(alarmService.createAlarm(lectureId, loggedInMember));
    }
}
