package com.dev.classmoa.controller;

import com.dev.classmoa.auth.annotation.LoginMember;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.interest.response.FindInterestLecturesResponse;
import com.dev.classmoa.dto.interest.response.FindInterestResponse;
import com.dev.classmoa.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    // 좋아요 한 강의 목록 조회 - 마이페이지
    @GetMapping("/user/interest")
    public ResponseEntity<List<FindInterestLecturesResponse>> getInterests(@LoginMember LoggedInMember member) {
        return ResponseEntity.ok(interestService.getInterestListByMember(member));
    }

    // 좋아요 한 강의 한개 조회
    @GetMapping("/interest/{lecture_id}")
    public ResponseEntity<FindInterestResponse> getInterest(@PathVariable("lecture_id") String lectureId, @LoginMember LoggedInMember member) {
        return ResponseEntity.ok(interestService.getIsInterested(lectureId, member));
    }

    // 좋아요 등록
    //TODO: PathVariable 확인 [가영]
    @PostMapping("/user/interest/{lecture_id}")
    public ResponseEntity<FindInterestResponse> createInterest(@PathVariable("lecture_id") String lectureId, @LoginMember LoggedInMember member) {
        return ResponseEntity.ok(interestService.createInterest(lectureId, member));
    }
}
