package com.dev.classmoa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Lecture.response.FindInterestLectures;
import com.dev.classmoa.service.InterestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InterestController {

	private final InterestService interestService;

	// 좋아요 한 강의 목록 조회 - 마이페이지
	@GetMapping("/interest")
	public ResponseEntity<List<FindInterestLectures>> getInterests(Member member){
		return ResponseEntity.ok(interestService.getLectureListByMember(member.getId()));
	}
	// 좋아요 한 강의 한개 조회
	@GetMapping("/interest/{lecture_id}")
	public Boolean getInterest(@PathVariable("lecture_id") String lectureId, Member member){
		return  interestService.getIsInterested(lectureId, member);
	}

	// 좋아요 등록
	//TODO: PathVariable 확인 [가영]
	@PostMapping("/interest/{lecture_id}")
	public Long createInterest(@PathVariable("lecture_id") String lectureId, Member member){
		return interestService.create(lectureId, member);
	}

	// 좋아요 삭제
	// TODO: 엔티티로 Request 를 받고 있는데.... [가영,창준]
	@DeleteMapping("/interest/{interest_id}")
	public void cancelInterest(@PathVariable("interest_id") Long interestId, Member member){
		interestService.cancel(interestId, member);
	}
}
